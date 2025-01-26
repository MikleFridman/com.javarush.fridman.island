package creatures.animals;

import location.Location;

import java.util.HashMap;
import java.util.Map;

public class Fox extends Predator {
    public final static double DEFAULT_WEIGHT = 20.0;
    public final static int MAX_COUNT = 60;
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();

    static {
        foodMap.put(Mouse.class, 75);
        foodMap.put(Rabbit.class, 60);
        foodMap.put(Duck.class, 50);
    }

    public Fox(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }
}
