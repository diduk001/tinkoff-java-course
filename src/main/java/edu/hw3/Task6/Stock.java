package edu.hw3.Task6;

public class Stock implements Comparable<Stock> {
    Integer value;

    public Stock(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.value = value;
    }

    @Override
    public int compareTo(Stock o) {
        return Integer.compare(o.value, this.value);
    }

    public Integer getValue() {
        return value;
    }
}
