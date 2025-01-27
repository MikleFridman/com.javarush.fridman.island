package threads;
import creatures.animals.Animal;
import location.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Life implements Runnable{
    private final Location location;
    private final List<Animal> animalsList = new ArrayList<>();
    private final Queue<Task> tasks  = new ConcurrentLinkedQueue<Task>();


    public Life(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        for (Class<? extends Animal> clazz : location.animalsMap.keySet()) {
            animalsList.addAll(location.animalsMap.get(clazz));
        }
        Collections.shuffle(animalsList);
        location.getPlant().grow();
        location.getLock().lock();
        try {
            for (Animal animal : animalsList) {
                tasks.add(new Task(animal));
            }
        } finally {
            location.getLock().unlock();
        }
        tasks.forEach(Task::doTask);
        tasks.clear();
    }
}
