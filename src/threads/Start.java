package threads;
import creatures.Plant;
import creatures.animals.Buffalo;
import creatures.animals.Rabbit;
import creatures.animals.Wolf;
import location.Island;
import location.Location;
import java.util.concurrent.ThreadLocalRandom;

public class Start extends Thread {

    public Start() {
        this.start();
    }

    @Override
    public void run() {
        Island island = Island.getInstance();

        for (int x = 1; x <= island.maxLongitude; x++) {
            for (int y = 1; y <= island.maxLatitude; y++) {
                island.locationsList.add(new Location(x,y));
            }
        }

        for (Location location : island.locationsList) {
            new Plant(location);
            for (int i = 1; i < ThreadLocalRandom.current().nextInt(4,20);i++) {
                new Buffalo(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current().nextInt(8,30);i++) {
                new Rabbit(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current().nextInt(3,10);i++) {
                new Wolf(location);
            }
        }

        while(!interrupted()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Location location : island.locationsList) {
                System.out.println(location.getInfo());
            }

            if (island.countAnimals <= 0) {
                interrupt();
            }
        }
    }
}

