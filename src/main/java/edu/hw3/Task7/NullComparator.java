package edu.hw3.Task7;

import java.util.Comparator;

public final class NullComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else {
            return 1;
        }
    }
}
