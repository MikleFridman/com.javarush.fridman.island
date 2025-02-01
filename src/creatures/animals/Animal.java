package creatures.animals;
import location.Island;
import location.Location;
import utils.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    public final static double DEFAULT_WEIGHT = 0;
    public final static int MAX_COUNT = 0;
    public final static int REPRODUCE_CHANCE = 10;
    public Location location;
    private long id;
    private double weight;
    private boolean hasChild;

    public Animal(Location location) {
        List<Animal> animalsList = location.animalsMap.get(this.getClass());
        if (animalsList != null) {
            if (animalsList.size() >= this.getMaxCount()) {
                return;
            }
        }
        this.location = location;
        setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        location.addAnimal(this);
        Util.setMsg("Родился " + this);
    }

    public abstract boolean eat();

    public void reproduce() {
        if (location.animalsMap.get(this.getClass()).size() < 2  || hasChild || getId() < 0) {
            return;
        }
        if (ThreadLocalRandom.current().nextInt(0, 100) <= getReproduceChance()) {
            try {
                this.getClass().getConstructor(new Class[]{Location.class}).newInstance(location);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                return;
            }
            hasChild = true;
            Util.setMsg(this + " успешно продолжил род ");
        } else {
            Util.setMsg("Не получилось у " + this);
        }
    }

    public void move() {
        if (getId() < 0) {
            return;
        }
        Location newLocation = null;
        int direction = ThreadLocalRandom.current().nextInt(1,5);
        switch (direction) {
            case 1 -> {
                if (location.y > 1) {
                    newLocation = Island.getInstance().getLocation(location.x, location.y - 1);
                    Util.setMsg(location + " " + this + " переместился вверх в " + newLocation);
                }
            }
            case 2 -> {
                if (location.x < Island.MAX_LATITUDE) {
                    newLocation = Island.getInstance().getLocation(location.x + 1, location.y);
                    Util.setMsg(location + " " + this + " переместился вправо в " + newLocation);
                }
            }
            case 3 -> {
                if (location.y < Island.MAX_LONGITUDE) {
                    newLocation = Island.getInstance().getLocation(location.x, location.y + 1);
                    Util.setMsg(location + " " + this + " переместился вниз в " + newLocation);
                }
            }
            case 4 -> {
                if (location.x > 1) {
                    newLocation = Island.getInstance().getLocation(location.x - 1, location.y);
                    Util.setMsg(location + " " + this + " переместился влево в " + newLocation);
                }
            }
        }
        if (location != newLocation && newLocation != null) {
            location.deleteAnimal(this);
            newLocation.addAnimal(this);
            location = newLocation;
        }
    }

    public void die() {
        location.deleteAnimal(this);
        Util.setMsg(this + " умер / был съеден");
        setId(-1);
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

    public int getReproduceChance() {
        try {
            return this.getClass().getField("REPRODUCE_CHANCE").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    public double getOriginalWeight() {
        try {
            return this.getClass().getField("DEFAULT_WEIGHT").getDouble(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    public int getMaxCount() {
        try {
            return this.getClass().getField("MAX_COUNT").getInt(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    public void decreaseWeight() {
        if (id < 0) {
            return;
        }
        setWeight((double) Math.round(getWeight() * 0.9 * 100) / 100);
        Util.setMsg(this + " похудел до " + getWeight());
        if (getRatioWeight() < 0.5) {
            die();
        }
    }

    public double getRatioWeight() {
        return (double) Math.round(getWeight() / getOriginalWeight() * 100) / 100;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getId();
    }
}
