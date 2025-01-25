package threads;
import location.Island;
import location.Location;

public class GrowTask implements Runnable{
    @Override
    public void run() {
        for (Location location : Island.getInstance().locationsList) {
            location.getLock().lock();
            try {
                location.getPlant().grow();
                System.out.println(location.getInfo());
            } finally {
                location.getLock().unlock();
            }
        }
    }
}
