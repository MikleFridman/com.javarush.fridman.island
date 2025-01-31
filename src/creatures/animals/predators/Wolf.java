package creatures.animals.predators;
import creatures.animals.herbivores.*;
import location.Location;
import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predator {
    public final static double DEFAULT_WEIGHT = 80.0;
    public final static int MAX_COUNT = 30;
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();
    public final static int REPRODUCE_CHANCE = 30;

    static {
        foodMap.put(Mouse.class, 75);
        foodMap.put(Rabbit.class, 60);
        foodMap.put(Sheep.class, 50);
        foodMap.put(Buffalo.class, 40);
    }

    public Wolf(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }
}
