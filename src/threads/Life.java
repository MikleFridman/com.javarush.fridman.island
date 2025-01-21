package threads;

import creatures.animals.Animal;

import java.lang.reflect.InvocationTargetException;

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
                Thread.sleep(1000);
                object.reproduce();
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
