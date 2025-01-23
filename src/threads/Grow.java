package threads;
import creatures.Plant;

public class Grow extends Thread {
    Plant object;

    public Grow(Plant o) {
        object = o;
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (object.getWeight() < Plant.MAX_WEIGHT) {
                object.increaseWeight();
            }
        }
    }
}
