package creatures;
import location.Location;
import threads.Grow;

public class Grass extends Creature {

    public final static int MAX_WEIGHT = 300;
    public Location location;
    private double weight;

    public Grass(Location location) {
        this.location = location;
        location.addCreature(this);
        //System.out.println(location + " добавлен объект " + this);
        new Grow(this);
    }

    public synchronized double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return location + " " + getClass().getSimpleName();
    }

    public void increaseWeight() {
        setWeight(getWeight() + 50);
    }

    public void decreaseWeight(double weight) {
        setWeight(getWeight() >= weight ? getWeight() - weight : 0);
    }

}
