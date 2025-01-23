package creatures.animals;
import creatures.Plant;
import location.Location;
import utils.Util;

public abstract class Herbivore extends Animal {
    public final static int SURVIVAL_CHANCE = 0;

    public Herbivore(Location location) {
        super(location);
    }

    public int getSurvivalChance() {
        try {
            return this.getClass().getField("SURVIVAL_CHANCE").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    @Override
    public void eat() {
        Plant plant = location.getPlant();
        if (plant == null || plant.getWeight() < 0.001 || getId() < 0) {
            return;
        }

        double foodWeight = Math.min(plant.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;
        if (foodWeight > 0) {
            plant.decreaseWeight(foodWeight);
            setWeight(getWeight() + foodWeight);
            Util.setMsg(this + " cъел " + foodWeight + " травы");
        }
    }
}
