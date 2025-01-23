package creatures.animals;
import creatures.Creature;
import location.Location;
import utils.Util;
import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(Location location) {
        super(location);
    }

    @Override
    public void eat() {
        List<Creature> listHerbivore;
        Herbivore prey;

        try {
            listHerbivore = location.getListHerbivore();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (listHerbivore == null || listHerbivore.isEmpty()) {
            Util.setMsg("Хищникам нечего есть");
            return;
        }

        prey = (Herbivore) listHerbivore.get(new Random().nextInt(0,listHerbivore.size()));
        try {
            if (new Random().nextInt(0, 100) <= prey.getSurvivalChance()) {
                Util.setMsg(prey + " убежал от " + this);
                return;
            }
        } catch (Exception e) {
            return;
        }

        double foodWeight = Math.min(prey.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;
        if (foodWeight > 0) {
            setWeight(getWeight() + foodWeight);
            Util.setMsg(this + " cъел " + foodWeight + " от " + prey);
            prey.die();
        }
    }
}
