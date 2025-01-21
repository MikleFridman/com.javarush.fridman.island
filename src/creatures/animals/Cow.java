package creatures.animals;

import location.Location;

public class Cow extends Herbivore {

    public final static double defaultWeight = 200;
    public final static int survivalChance = 40;

    public Cow(Location location) {
        super(location);
        setWeight(defaultWeight);
    }

}
