package creatures.animals;
import location.Location;

public class Rabbit extends Herbivore {

    public final static double defaultWeight = 10;
    public final static int survivalChance = 60;
    public final static int reproduceChance = 70;

    public Rabbit(Location location) {
        super(location);
        setWeight(defaultWeight);
    }

}
