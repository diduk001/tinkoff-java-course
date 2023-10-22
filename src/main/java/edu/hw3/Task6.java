package edu.hw3;

import java.util.PriorityQueue;

public class Task6 {
    interface StockMarketInterface {
        void add(Stock stock);

        void remove(Stock stock);

        Stock mostValuableStock();
    }

    static class Stock implements Comparable<Stock> {
        Integer value;

        Stock(int value) {
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

    static class StockMarket implements StockMarketInterface {
        private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>();

        @Override
        public void add(Stock stock) {
            stockQueue.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            stockQueue.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            if (stockQueue.isEmpty()) {
                return null;
            }

            return stockQueue.peek();
        }
    }
}
