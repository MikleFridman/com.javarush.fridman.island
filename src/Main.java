import location.Island;
import location.Location;
import threads.Life;
import threads.Monitor;
import threads.Start;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Start();
        Monitor monitor = new Monitor();
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(8);
        for (Location location : Island.getInstance().locationsList) {
            Life life = new Life(location);
            mainPool.scheduleAtFixedRate(life, 1, 3, TimeUnit.SECONDS);
        }
        mainPool.scheduleAtFixedRate(monitor,2,3, TimeUnit.SECONDS);
    }
}