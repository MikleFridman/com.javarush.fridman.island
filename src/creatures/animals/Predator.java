package creatures.animals;
import location.Location;
import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(Location location) {
        super(location);
    }

    @Override
    public void eat() {
        List<Herbivore> listHerbivore;
        Herbivore prey;

        try {
            listHerbivore = location.getListHerbivore();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (listHerbivore == null || listHerbivore.isEmpty()) {
            return;
        }

        prey = listHerbivore.get(new Random().nextInt(0,listHerbivore.size()));

        if (new Random().nextInt(0, 100) <= prey.getSurvivalChance()) {
            //System.out.println(prey + " убежал от " + this);
            return;
        }

        double foodWeight = Math.min(prey.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;

        if (foodWeight < 0.001) {
            return;
        }

        prey.die();
        setWeight(getWeight() + foodWeight);
        //System.out.println(this + " cъел " + foodWeight + " от " + prey);
    }

}
