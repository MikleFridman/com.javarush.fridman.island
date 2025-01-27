package creatures.animals.herbivores;
import location.Location;

public class Duck extends Herbivore {
    public final static double DEFAULT_WEIGHT = 1;
    public final static int MAX_COUNT = 150;
    public final static int REPRODUCE_CHANCE = 70;

    public Duck(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
