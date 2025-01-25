package threads;
import creatures.animals.Animal;
import location.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LifeTask implements Runnable{
    private final Location location;
    private final List<Animal> animalsList = new ArrayList<>();

    public LifeTask(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        for (Class<? extends Animal> clazz : location.animalsMap.keySet()) {
            animalsList.addAll(location.animalsMap.get(clazz));
        }
        Collections.shuffle(animalsList);
        location.getLock().lock();
        try {
            for (Animal animal : animalsList) {
                animal.eat();
                animal.reproduce();
                animal.move();
                animal.decreaseWeight();
            }
        } finally {
            location.getLock().unlock();
        }
    }
}
