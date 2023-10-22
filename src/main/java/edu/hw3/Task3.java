package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;

final public class Task3 {
    private Task3() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    static public HashMap<Object, Integer> freqDict(ArrayList<Object> list) {
        HashMap<Object, Integer> result = new HashMap<>();
        for (Object object : list) {
            if (!result.containsKey(object)) {
                result.put(object, 0);
            }
            Integer oldValue = result.get(object);
            result.put(object, oldValue + 1);
        }

        return result;
    }
}
