package creatures.animals.herbivores;
import location.Location;

public class Sheep extends Herbivore {
    public final static double DEFAULT_WEIGHT = 30;
    public final static int MAX_COUNT = 150;
    public final static int REPRODUCE_CHANCE = 60;

    public Sheep(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
