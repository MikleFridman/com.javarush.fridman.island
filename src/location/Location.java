package location;
import creatures.Creature;
import creatures.Grass;
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

    public ArrayList<Creature> getCreaturesArray(Class<? extends Creature> clazz) {
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

    public synchronized String getDashboard(){
        String dashboard = "" + this;
        double weightGrass = 0;

        for (Class<? extends Creature> o : creaturesMap.keySet()) {
            if (o != Grass.class) {
                dashboard = dashboard + " " + o.getSimpleName() +
                        ": " + creaturesMap.get(o).toArray().length + ";";
            }
        }

        if (getGrass() != null) {
            weightGrass = (double) Math.round(getGrass().getWeight() * 100) / 100;
        }

        dashboard = dashboard + " Grass: " + weightGrass;
        return dashboard;
    }

    public Grass getGrass() {
        List<Creature> listGrass = getCreaturesArray(Grass.class);

        if (listGrass.get(0) != null) {
            Grass grass = (Grass) listGrass.get(0);
            if (grass.getWeight() > 0) {
                return grass;
            }
        }

        return null;
    }

    public synchronized List<Herbivore> getListHerbivore() throws ClassNotFoundException {
        List<Herbivore> listHerbivore = new CopyOnWriteArrayList<>();

        for (Class<? extends Creature> o : creaturesMap.keySet()) {
            if (Herbivore.class.isAssignableFrom(o)) {
                for (Creature creature : creaturesMap.get(o)) {
                    listHerbivore.add((Herbivore) creature);
                }
            }
        }

        return listHerbivore;
    }

}
