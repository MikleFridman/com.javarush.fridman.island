package threads;
import creatures.Plant;
import creatures.animals.herbivores.*;
import creatures.animals.predators.*;
import location.Island;
import location.Location;
import utils.AnimalFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Start extends Thread {
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
            AnimalFactory.born(Buffalo.class, location);
            AnimalFactory.born(Sheep.class, location);
            AnimalFactory.born(Rabbit.class, location);
            AnimalFactory.born(Mouse.class, location);
            AnimalFactory.born(Duck.class, location);
            AnimalFactory.born(Eagle.class, location);
            AnimalFactory.born(Wolf.class, location);
            AnimalFactory.born(Fox.class, location);
        }
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(8);
        mainPool.scheduleWithFixedDelay(new ExecutorService(mainPool), 1,2, TimeUnit.SECONDS);
    }
}

