package location;
import creatures.Creature;
import creatures.Plant;
import creatures.animals.Animal;
import creatures.animals.Herbivore;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    public int x;
    public int y;
    private Plant plant;
    private final Lock lock = new ReentrantLock();
    public final Map<Class<? extends Animal>, ArrayList<Animal>> animalsMap = new HashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Lock getLock() {
        return lock;
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

    public ArrayList<Animal> getCreatureArray(Class<? extends Animal> clazz) {
        return animalsMap.get(clazz);
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
    }

    public void deleteAnimal(Animal o) {
        List<Animal> list = animalsMap.get(o.getClass());
        list.remove(o);
    }

    public String getInfo(){
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
        List<Animal> listHerbivore = new CopyOnWriteArrayList<>();
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
