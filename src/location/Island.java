package location;
import creatures.animals.Animal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {
    public static final int MAX_LONGITUDE = 100;
    public static final int MAX_LATITUDE = 20;
    public static final int MAX_CYCLES = 31;
    private static Island instance;
    public int cycle = 1;
    public final List<Location> locationsList = new ArrayList<>();
    public final Map<Class<? extends Animal>, Integer> infoMap = new HashMap<>();
    public volatile double weightPlant;

    public static Island getInstance() {
        if (instance == null) {
            instance = new Island();
        }
        return instance;
    }

    public Location getLocation(int x, int y) {
        for (Location location : locationsList) {
            if (location.x == x && location.y == y)
                return location;
        }
        return null;
    }

    public synchronized String getInfo() {
        infoMap.clear();
        weightPlant = 0;
        for (Location location : locationsList) {
            weightPlant = weightPlant + location.getPlant().getWeight();
            for (Class<? extends Animal> animalClass : location.animalsMap.keySet()) {
                if (infoMap.containsKey(animalClass)) {
                    infoMap.put(animalClass, infoMap.get(animalClass)
                            + location.animalsMap.get(animalClass).size());
                } else {
                    infoMap.put(animalClass, location.animalsMap.get(animalClass).size());
                }
            }
        }
        StringBuilder info = new StringBuilder("Day: " + cycle + " ");
        for (Class<?> o : infoMap.keySet()) {
            if (infoMap.get(o) > 0) {
                info.append(o.getSimpleName()).append(": ")
                    .append(infoMap.get(o)).append(" ");
            }
        }
        info.append("Plant: ").append((double) Math.round(weightPlant * 100) / 100);
        return info.toString();
    }
}
