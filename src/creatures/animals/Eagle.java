package creatures.animals;
import location.Location;
import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predator{
    public final static double DEFAULT_WEIGHT = 10.0;
    public final static int MAX_COUNT = 40;
    public final static int REPRODUCE_CHANCE = 70;
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();

    static {
        foodMap.put(Mouse.class, 75);
        foodMap.put(Duck.class, 60);
        foodMap.put(Rabbit.class, 50);
    }

    public Eagle(Location location) {
        super(location);
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }
}
