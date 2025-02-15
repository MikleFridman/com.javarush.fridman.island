package creatures.animals.herbivores;
import creatures.Plant;
import creatures.animals.Animal;
import location.Location;
import utils.Util;

public abstract class Herbivore extends Animal {

    public Herbivore(Location location) {
        super(location);
    }

    @Override
    public boolean eat() {
        Plant plant = location.getPlant();
        if (plant == null || plant.getWeight() < 0.001 || getId() < 0) {
            return false;
        }
        double foodWeight = Math.min(plant.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;
        if (foodWeight > 0) {
            plant.decreaseWeight(foodWeight);
            setWeight(getWeight() + foodWeight);
            Util.setMsg(this + " cъел " + foodWeight + " травы");
        }
        return true;
    }
}
