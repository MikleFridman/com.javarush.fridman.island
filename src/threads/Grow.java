package creatures;

public class Grow extends Thread {

    Grass object;

    public Grow(Grass o) {
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

            object.increaseWeight();

            if (object.getWeight() >= Grass.MAX_WEIGHT) {
                this.interrupt();
            }
        }


    }
}
