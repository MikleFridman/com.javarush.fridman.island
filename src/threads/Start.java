package threads;

import location.Location;

public class Start extends Thread {

    Location location;

    public Start(Location location) {
        this.location = location;
        this.start();
    }

    @Override
    public void run() {

        while(!isInterrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(location.getDashboard());
        }

    }
}

