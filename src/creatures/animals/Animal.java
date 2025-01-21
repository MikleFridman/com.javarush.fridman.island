package creatures.animals;
import creatures.Creature;
import location.Location;
import threads.Life;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Creature {

    public Location location;
    private long id;
    private double weight;
    public final static double defaultWeight = 0;
    public final static int reproduceChance = 50;

    public Animal(Location location) {
        this.location = location;
        setId(new Random().nextLong());
        location.addCreature(this);
        new Life(this);
    }

    public void eat() {

    }

    public void reproduce() {
        ArrayList<Creature> animalsList = location.creaturesMap.get(this.getClass());
        if (animalsList.size() < 2) {
            return;
        }

        if (new Random().nextInt(0, 100) >= this.getReproduceChance()) {
            //System.out.println("Не получилось у " + this);
            return;
        }

        Animal child;

        try {
            child = this.getClass().getConstructor(new Class[]{Location.class}).newInstance(location);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(this + " успешно продолжил род " + child);
    }

    public void run() {

    }

    public void die() {
        this.setWeight(0);
        location.deleteCreature(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = Math.min(weight, getOriginalWeight());
    }

    public double getOriginalWeight() {

        try {
            return this.getClass().getField("defaultWeight").getDouble(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }

    }

    public void decreaseWeight() {
        double currentWeight = getWeight();
        double originalWeight = getOriginalWeight();
        double newWeight = (double) Math.round(currentWeight * 0.9 * 100) / 100;

        setWeight(newWeight < originalWeight / 2 ? 0 : newWeight);

        if (getWeight() > 0.1) {
            //System.out.println(this + " похудел до " + getWeight());
        }

    }

    public int getReproduceChance() {

        try {
            return this.getClass().getField("reproduceChance").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getId();
    }

}
