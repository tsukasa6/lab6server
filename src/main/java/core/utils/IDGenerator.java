package core.utils;

import java.util.HashSet;
import java.util.Random;

public class IDGenerator {
    static HashSet<Integer> IDsHashSet = new HashSet<>();

    public static int generateId() {
        int id = new Random().nextInt(Integer.MAX_VALUE);

        if (IDsHashSet.contains(id)) {
            id = new Random().nextInt(Integer.MAX_VALUE);
        }
        IDsHashSet.add(id);

        return id;
    }

    public static Integer generateID(Integer ID) {
        Integer id = ID;

        if (IDsHashSet.contains(id)) {
            while (IDsHashSet.contains(id)) {
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }
        IDsHashSet.add(id);

        return id;
    }
}

// Возможно нужно доработать класс.
// УДАЛИТЬ ПО ЗАВЕРШЕНИЮ ПРОЕКТА.