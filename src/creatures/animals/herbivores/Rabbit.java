package creatures.animals.herbivores;
import location.Location;

public class Rabbit extends Herbivore {
    public final static double DEFAULT_WEIGHT = 10;
    public final static int MAX_COUNT = 300;
    public final static int REPRODUCE_CHANCE = 80;

    public Rabbit(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }
}
