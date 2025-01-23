package creatures.animals;
import location.Location;

public class Rabbit extends Herbivore {
    public final static double DEFAULT_WEIGHT = 10;
    public final static int MAX_COUNT = 200;
    public final static int SURVIVAL_CHANCE = 60;
    public final static int REPRODUCE_CHANCE = 70;

    public Rabbit(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
