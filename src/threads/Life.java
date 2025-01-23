package threads;
import creatures.animals.Animal;
import location.Island;

public class Life extends Thread {
    Animal object;

    public Life(Animal o) {
        object = o;
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                object.eat();
                Thread.sleep(1500);
                object.reproduce();
                Thread.sleep(1500);
                object.move();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            object.decreaseWeight();
            if (object.getId() < 0) {
                interrupt();
            }
        }
    }
}
