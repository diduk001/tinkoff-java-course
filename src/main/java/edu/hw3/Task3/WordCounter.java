package edu.hw3.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordCounter {
    private WordCounter() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static Map<Object, Integer> freqDict(List<Object> list) {
        Map<Object, Integer> result = new HashMap<>();
        for (Object object : list) {
            result.put(object, result.getOrDefault(object, 0) + 1);
        }

        return result;
    }
}
