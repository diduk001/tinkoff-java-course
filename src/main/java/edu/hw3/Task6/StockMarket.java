package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StockMarket implements StockMarketInterface {
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
