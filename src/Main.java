import creatures.Grass;
import threads.Start;
import creatures.animals.Cow;
import creatures.animals.Rabbit;
import creatures.animals.Wolf;
import location.Location;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Location location = new Location(1,1);
        new Start(location);
        new Grass(location);
        new Cow(location);
        new Cow(location);
        new Cow(location);
        new Wolf(location);
        Thread.sleep(2000);
        new Rabbit(location);
        new Rabbit(location);
        Thread.sleep(1000);
        new Rabbit(location);
        Thread.sleep(3000);
        new Wolf(location);
        new Rabbit(location);
        new Rabbit(location);
        Thread.sleep(3000);
        new Wolf(location);
        new Rabbit(location);
        new Cow(location);
        new Rabbit(location);
        Thread.sleep(2000);
        new Rabbit(location);
        new Rabbit(location);
        new Cow(location);
        new Rabbit(location);
        Thread.sleep(1000);
        new Rabbit(location);
        new Rabbit(location);
        new Rabbit(location);
        new Rabbit(location);
        new Cow(location);
    }
}