package creatures.animals;
import location.Location;

public class Wolf extends Predator {

    public final static double defaultWeight = 80.0;

    public Wolf(Location location) {
        super(location);
        setWeight(defaultWeight);
    }

}
