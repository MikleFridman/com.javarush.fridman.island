package animals;

import java.util.Random;

public abstract class Animal extends Creature {

    private long id;
    private int weight;
    public final static int defaultWeight = 0;

    public Animal() {
        setId(new Random().nextLong());
        System.out.println("Родился " + this);
        new Life(this);
    }

    public void eat() {

    }

    public void reproduce() {

    }

    public void run() {

    }

    public void die() {
        System.out.println(this + " помер от голода");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void decreaseWeight() {
        int currentWeight = getWeight();
        int originalWeight = 0;

        try {
            originalWeight = this.getClass().getField("defaultWeight").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int newWeight = currentWeight * 9 / 10;
        setWeight(newWeight < originalWeight / 2 ? 0 : newWeight);

        if (getWeight() > 0) {
            System.out.println(this + " похудел до " + getWeight());
        }

    }

    @Override
    public String toString() {
        return getClass().getName() + " " + getId();
    }

}
