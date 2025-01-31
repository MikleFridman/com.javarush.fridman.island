package utils;
import location.Island;

import java.util.concurrent.ScheduledExecutorService;

public class Dashboard implements Runnable{
    public ScheduledExecutorService pool;

    public Dashboard(ScheduledExecutorService servicePool) {
        pool = servicePool;
    }

    @Override
    public void run(){
        if (Island.getInstance().cycle >= Island.MAX_CYCLES) {
            pool.shutdown();
        }
        System.out.println(Island.getInstance().getInfo());
    }
}
