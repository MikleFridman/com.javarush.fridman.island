package location;
import java.util.ArrayList;
import java.util.List;

public class Island {
    public static final int MAX_LONGITUDE = 10;
    public static final int MAX_LATITUDE = 10;
    private static Island instance;
    public final List<Location> locationsList = new ArrayList<>();

    private Island() {

    }

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
}
