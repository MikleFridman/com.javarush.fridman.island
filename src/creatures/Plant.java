package creatures;
import location.Location;
import utils.Util;

public class Plant {
    public final static int GROW_RATE = 1200;
    public final static int MAX_WEIGHT = 3600;
    public Location location;
    private double weight;

    public Plant(Location location) {
        this.location = location;
        location.setPlant(this);
    }

    public synchronized double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void grow() {
        if (getWeight() + GROW_RATE <= MAX_WEIGHT) {
            setWeight(getWeight() + GROW_RATE);
            Util.setMsg(location + " Растет трава");
        }
    }

    public void decreaseWeight(double weight) {
        setWeight(getWeight() >= weight ? getWeight() - weight : 0);
    }

    @Override
    public String toString() {
        return location + " " + getClass().getSimpleName();
    }
}
