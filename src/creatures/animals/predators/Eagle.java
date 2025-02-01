package creatures.animals.predators;
import creatures.animals.herbivores.Duck;
import creatures.animals.herbivores.Herbivore;
import creatures.animals.herbivores.Mouse;
import creatures.animals.herbivores.Rabbit;
import location.Location;
import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predator {
    public final static double DEFAULT_WEIGHT = 10.0;
    public final static int MAX_COUNT = 30;
    public final static int REPRODUCE_CHANCE = 40;
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();

    static {
        foodMap.put(Mouse.class, 85);
        foodMap.put(Duck.class, 60);
        foodMap.put(Rabbit.class, 50);
    }

    public Eagle(Location location) {
        super(location);
        setWeight(DEFAULT_WEIGHT);
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }
}
