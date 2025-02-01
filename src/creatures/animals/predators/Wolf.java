package creatures.animals.predators;
import creatures.animals.herbivores.*;
import location.Location;
import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predator {
    public final static double DEFAULT_WEIGHT = 80.0;
    public final static int MAX_COUNT = 60;
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();

    static {
        foodMap.put(Mouse.class, 55);
        foodMap.put(Rabbit.class, 70);
        foodMap.put(Sheep.class, 90);
        foodMap.put(Buffalo.class, 70);
    }

    public Wolf(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }
}
