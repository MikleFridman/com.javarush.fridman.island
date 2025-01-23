package creatures.animals;
import location.Location;

public class Wolf extends Predator {
    public final static double DEFAULT_WEIGHT = 80.0;
    public final static int MAX_COUNT = 50;

    public Wolf(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
