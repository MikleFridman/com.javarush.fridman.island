import creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class Location {

    public int x;
    public int y;
    public List<Creature> creatureList = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
