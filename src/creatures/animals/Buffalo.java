package creatures.animals;
import location.Location;

public class Buffalo extends Herbivore {
    public final static double DEFAULT_WEIGHT = 200;
    public final static int MAX_COUNT = 30;
    public final static int SURVIVAL_CHANCE = 40;
    public final static int REPRODUCE_CHANCE = 40;

    public Buffalo(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
