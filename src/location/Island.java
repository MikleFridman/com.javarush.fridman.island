package location;
import java.util.ArrayList;
import java.util.List;

public class Island {
    public final int maxLongitude = 1;
    public final int maxLatitude = 1;
    private static Island instance;
    public final List<Location> locationsList = new ArrayList<>();
    public int countAnimals;

    private Island() {

    }

    public static Island getInstance() {
        if (instance == null) {
            instance = new Island();
        }

        return instance;
    }
}
