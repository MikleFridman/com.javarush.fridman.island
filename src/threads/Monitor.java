package threads;
import location.Island;
import location.Location;

public class Monitor implements Runnable{
    @Override
    public void run() {
        for (Location location : Island.getInstance().locationsList) {
            System.out.println(location.getInfo());
        }
    }
}
