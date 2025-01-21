package creatures.animals;

public class Life extends Thread {

    Animal object;

    public Life(Animal o) {
        object = o;
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            object.eat();
            object.reproduce();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            object.decreaseWeight();

            if (object.getWeight() <= 0.001) {
                this.interrupt();
            }
        }

        object.die();
    }

}
