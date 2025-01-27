package threads;
import creatures.animals.Animal;

public class Task {
    private final Animal animal;

    public Task(Animal animal) {
        this.animal = animal;
    }

    public void doTask() {
        animal.eat();
        animal.reproduce();
        animal.move();
        animal.decreaseWeight();
    }
}
