package location;
import creatures.Plant;
import creatures.animals.Animal;
import creatures.animals.herbivores.Herbivore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    public int x;
    public int y;
    private Plant plant;
    public final Map<Class<? extends Animal>, CopyOnWriteArrayList<Animal>> animalsMap = new ConcurrentHashMap<>();

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
            var newList = new CopyOnWriteArrayList<Animal>();
            newList.add(o);
            animalsMap.put(o.getClass(),newList);
        } else {
            list.add(o);
        }
    }

    public void deleteAnimal(Animal o) {
        List<Animal> list = animalsMap.get(o.getClass());
        list.remove(o);
    }

    public synchronized String getInfo(){
        StringBuilder info = new StringBuilder(this + " ");
        for (Class<? extends Animal> o : animalsMap.keySet()) {
            info.append(o.getSimpleName()).append(": ")
                .append(animalsMap.get(o).size()).append("; ");
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
