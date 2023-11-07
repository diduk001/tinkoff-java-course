package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BackwardIterator<T> implements Iterator<T> {
    private final ListIterator<T> iterator;

    public BackwardIterator(List<T> collection) {
        iterator = collection.listIterator(collection.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        return iterator.previous();
    }
}
