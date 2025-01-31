package location;
import creatures.Plant;
import creatures.animals.Animal;
import creatures.animals.herbivores.Herbivore;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Location {
    public int x;
    public int y;
    private Plant plant;
    public final Map<Class<? extends Animal>, ArrayList<Animal>> animalsMap = new ConcurrentHashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void addAnimal(Animal o) {
        List<Animal> list = animalsMap.get(o.getClass());
        if (list == null) {
            var newList = new ArrayList<Animal>();
            newList.add(o);
            animalsMap.put(o.getClass(),newList);
        } else {
            list.add(o);
        }
        Island.getInstance().addAnimal(o);
    }

    public void deleteAnimal(Animal o) {
        List<Animal> list = animalsMap.get(o.getClass());
        list.remove(o);
        Island.getInstance().deleteAnimal(o);
    }

    public synchronized String getInfo(){
        StringBuilder info = new StringBuilder("" + this);
        for (Class<? extends Animal> o : animalsMap.keySet()) {
            info.append(" ").append(o.getSimpleName()).append(": ")
                .append(animalsMap.get(o).toArray().length).append(";");
        }
        double weightPlant = (double) Math.round(getPlant().getWeight() * 100) / 100;
        info.append(" Plant: ").append(weightPlant);
        return info.toString();
    }

    public synchronized List<Animal> getListHerbivore(Set<Class<? extends Herbivore>> classesSet) {
        List<Animal> listHerbivore = new ArrayList<>();
        for (Class<? extends Animal> clazz : animalsMap.keySet()) {
            if (Herbivore.class.isAssignableFrom(clazz)) {
                if (classesSet.contains(clazz) || classesSet.contains(Herbivore.class)) {
                    listHerbivore.addAll(animalsMap.get(clazz));
                }
            }
        }
        return listHerbivore;
    }
}
