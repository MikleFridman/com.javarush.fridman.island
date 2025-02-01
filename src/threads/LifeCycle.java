package threads;
import creatures.animals.Animal;
import location.Location;
import utils.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LifeCycle implements Runnable{
    private final Location location;
    private final List<Animal> animalsList = new ArrayList<>();
    private final Queue<Task> tasks  = new ConcurrentLinkedQueue<>();

    public LifeCycle(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        for (Class<? extends Animal> animalClass : location.animalsMap.keySet()) {
            animalsList.addAll(location.animalsMap.get(animalClass));
        }
        Collections.shuffle(animalsList);
        location.getPlant().grow();
        for (Animal animal : animalsList) {
            if (animal.getId() > 0) {
                tasks.add(new Task(animal));
            } else {
                animal.location.deleteAnimal(animal);
            }
        }
        tasks.forEach(Task::doTask);
        tasks.clear();
        Util.setMsg(location.getInfo());
    }
}
