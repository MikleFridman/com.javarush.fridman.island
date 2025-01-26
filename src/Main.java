import location.Island;
import location.Location;
import threads.LifeTask;
import threads.Start;
import threads.GrowTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Start();
        GrowTask growTask = new GrowTask();
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(6);
        mainPool.scheduleAtFixedRate(growTask,2,3, TimeUnit.SECONDS);
        for (Location location : Island.getInstance().locationsList) {
            LifeTask lifeTask = new LifeTask(location);
            mainPool.scheduleAtFixedRate(lifeTask, 3, 3, TimeUnit.SECONDS);
        }
    }
}