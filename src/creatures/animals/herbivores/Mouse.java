package creatures.animals.herbivores;
import location.Location;

public class Mouse extends Herbivore {
    public final static double DEFAULT_WEIGHT = 0.1;
    public final static int MAX_COUNT = 200;
    public final static int REPRODUCE_CHANCE = 80;

    public Mouse(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
