package creatures.animals;
import creatures.Grass;
import location.Location;

public abstract class Herbivore extends Animal {

    public final static int survivalChance = 0;

    public Herbivore(Location location) {
        super(location);
    }

    public int getSurvivalChance() {

        try {
            return this.getClass().getField("survivalChance").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }

    }

    @Override
    public void eat() {

        Grass grass = location.getGrass();

        if (grass == null) {
            return;
        }

        double foodWeight = Math.min(grass.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;

        if (foodWeight < 0.001) {
            return;
        }

        grass.decreaseWeight(foodWeight);
        setWeight(getWeight() + foodWeight);
        //System.out.println(this + " cъел " + foodWeight + " травы");
    }

}
