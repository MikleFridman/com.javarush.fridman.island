package creatures.animals.predators;
import creatures.animals.Animal;
import creatures.animals.herbivores.Herbivore;
import location.Location;
import utils.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public final static Map<Class<? extends Herbivore>, Integer> foodMap = new HashMap<>();

    static {
        foodMap.put(Herbivore.class, 50);
    }

    public Predator(Location location) {
        super(location);
    }

    @Override
    public boolean eat() {
        if (getId() < 0) {
            return false;
        }
        List<Animal> listHerbivore;
        listHerbivore = location.getListHerbivore(getFoodMap().keySet());
        if (listHerbivore == null || listHerbivore.isEmpty()) {
            return false;
        }
        Collections.shuffle(listHerbivore);
        Herbivore prey = (Herbivore) listHerbivore.get(0);
        if (prey.getId() < 0) {
            return false;
        }
        if (ThreadLocalRandom.current().nextInt(0, 100) >= getFoodChance(prey.getClass())) {
            Util.setMsg(prey + " убежал от " + this);
            return false;
        }
        double foodWeight = Math.min(prey.getWeight(), getOriginalWeight() - getWeight());
        foodWeight = (double) Math.round(foodWeight * 100) / 100;
        if (foodWeight > 0) {
            setWeight(getWeight() + foodWeight);
            Util.setMsg(this + " cъел " + foodWeight + " от " + prey);
            prey.die();
        }
        return true;
    }

    public Map<Class<? extends Herbivore>, Integer> getFoodMap() {
        return foodMap;
    }

    public int getFoodChance(Class<? extends Herbivore> clazz) {
        return getFoodMap().get(clazz);
    }
}
