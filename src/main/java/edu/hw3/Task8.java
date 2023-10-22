package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Task8 {
    static class BackwardIterator<T> implements Iterator<T> {
        private final ListIterator<T> iterator;

        BackwardIterator(List<T> collection) {
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
}
