package utils;
import creatures.animals.Animal;
import creatures.animals.herbivores.*;
import creatures.animals.predators.Eagle;
import creatures.animals.predators.Fox;
import creatures.animals.predators.Wolf;
import location.Location;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory {

    public static void born(Class<? extends Animal> clazz, Location location) {
        if (clazz == Buffalo.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Buffalo.MAX_COUNT / 2); i++) {
                new Buffalo(location);
            }
        }
        if (clazz == Sheep.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Sheep.MAX_COUNT / 2); i++) {
                new Sheep(location);
            }
        }
        if (clazz == Rabbit.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Rabbit.MAX_COUNT / 2); i++) {
                new Rabbit(location);
            }
        }
        if (clazz == Mouse.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Mouse.MAX_COUNT / 2); i++) {
                new Mouse(location);
            }
        }
        if (clazz == Duck.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Duck.MAX_COUNT / 2); i++) {
                new Duck(location);
            }
        }
        if (clazz == Eagle.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Eagle.MAX_COUNT / 2); i++) {
                new Eagle(location);
            }
        }
        if (clazz == Wolf.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Wolf.MAX_COUNT / 2); i++) {
                new Wolf(location);
            }
        }
        if (clazz == Fox.class) {
            for (int i = 0; i < ThreadLocalRandom.current()
                    .nextInt(4, Fox.MAX_COUNT / 2); i++) {
                new Fox(location);
            }
        }
    }
}
