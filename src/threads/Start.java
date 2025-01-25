package threads;
import creatures.Plant;
import creatures.animals.*;
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
        for (int x = 1; x <= Island.MAX_LONGITUDE; x++) {
            for (int y = 1; y <= Island.MAX_LATITUDE; y++) {
                island.locationsList.add(new Location(x,y));
            }
        }
        for (Location location : island.locationsList) {
            new Plant(location);
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(4,Buffalo.MAX_COUNT / 2);i++) {
                new Buffalo(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(8,Rabbit.MAX_COUNT / 2);i++) {
                new Rabbit(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(2,Wolf.MAX_COUNT / 2);i++) {
                new Wolf(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(2,Duck.MAX_COUNT / 2);i++) {
                new Duck(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(10, Mouse.MAX_COUNT / 2); i++) {
                new Mouse(location);
            }
            for (int i = 1; i < ThreadLocalRandom.current()
                    .nextInt(2,Eagle.MAX_COUNT / 2);i++) {
                new Eagle(location);
            }
        }
    }
}

