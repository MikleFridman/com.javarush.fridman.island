package location;
import creatures.Creature;
import creatures.Plant;
import creatures.animals.Herbivore;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    public int x;
    public int y;
    public final Map<Class<? extends Creature>, ArrayList<Creature>> creaturesMap = new HashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public ArrayList<Creature> getCreatureArray(Class<? extends Creature> clazz) {
        return creaturesMap.get(clazz);
    }

    public void addCreature(Creature o) {
        List<Creature> list = creaturesMap.get(o.getClass());
        if (list == null) {
            var newList = new ArrayList<Creature>();
            newList.add(o);
            creaturesMap.put(o.getClass(),newList);
        } else {
            list.add(o);
        }

    }

    public void deleteCreature(Creature o) {
        List<Creature> list = creaturesMap.get(o.getClass());
        list.remove(o);
    }

    public String getInfo(){
        double weightPlant = 0;
        StringBuilder info = new StringBuilder("" + this);

        for (Class<? extends Creature> o : creaturesMap.keySet()) {
            if (o != Plant.class) {
                info.append(" ").append(o.getSimpleName()).append(": ")
                    .append(creaturesMap.get(o).toArray().length).append(";");
            }
        }

        if (getPlant() != null) {
            weightPlant = (double) Math.round(getPlant().getWeight() * 100) / 100;
        }

        info.append(" Plant: ").append(weightPlant);
        return info.toString();
    }

    public Plant getPlant() {
        List<Creature> listGrass = getCreatureArray(Plant.class);

        if (listGrass.get(0) != null) {
            Plant plant = (Plant) listGrass.get(0);
            if (plant.getWeight() > 0) {
                return plant;
            }
        }
        return null;
    }

    public synchronized List<Creature> getListHerbivore() throws ClassNotFoundException {
        List<Creature> listHerbivore = new CopyOnWriteArrayList<>();

        for (Class<? extends Creature> o : creaturesMap.keySet()) {
            if (Herbivore.class.isAssignableFrom(o)) {
                listHerbivore.addAll(creaturesMap.get(o));
            }
        }
        return listHerbivore;
    }
}
