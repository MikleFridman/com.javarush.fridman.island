package location;
import creatures.animals.Animal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Island {
    public static final int MAX_LONGITUDE = 20;
    public static final int MAX_LATITUDE = 10;
    public static final int MAX_CYCLES = 20;
    private static Island instance;
    public int cycle = 1;
    public final List<Location> locationsList = new CopyOnWriteArrayList<>();
    public final Map<Class<?>, Integer> infoMap = new ConcurrentHashMap<>();

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

    public synchronized void addAnimal(Animal o) {
        if (infoMap.containsKey(o.getClass())) {
            infoMap.put(o.getClass(), infoMap.get(o.getClass()) + 1);
        } else {
            infoMap.put(o.getClass(), 1);
        }
    }

    public synchronized void deleteAnimal(Animal o) {
        if (infoMap.containsKey(o.getClass())) {
            infoMap.put(o.getClass(), Math.max(infoMap.get(o.getClass()) - 1, 0));
        }
    }

    public synchronized String getInfo() {
        StringBuilder info = new StringBuilder("Day: " + cycle + " ");
        for (Class<?> o : infoMap.keySet()) {
            info.append(o.getSimpleName()).append(": ").append(infoMap.get(o)).append(" ");
        }
        ++cycle;
        return info.toString();
    }
}
