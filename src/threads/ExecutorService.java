package threads;
import location.Island;
import location.Location;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorService implements Runnable{
    public ScheduledExecutorService pool;

    public ExecutorService(ScheduledExecutorService servicePool) {
        pool = servicePool;
    }

    @Override
    public void run(){
        for (Location location : Island.getInstance().locationsList) {
            pool.submit(new LifeCycle(location));
        }
        System.out.println(Island.getInstance().getInfo());
        ++Island.getInstance().cycle;
        if (Island.getInstance().cycle > Island.MAX_CYCLES) {
            pool.shutdown();
        }
    }
}
