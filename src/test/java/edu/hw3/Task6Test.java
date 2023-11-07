package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Тест без удаления с числами 1, 2, 3, 4, 5")
    void testWithoutRemove12345() {
        StockMarket stockMarket = new StockMarket();

        Stock maxEmpty = stockMarket.mostValuableStock();
        stockMarket.add(new Stock(1));
        Integer max1 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(2));
        Integer max2 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(3));
        Integer max3 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(4));
        Integer max4 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(5));
        Integer max5 = stockMarket.mostValuableStock().getValue();

        assertThat(maxEmpty).isNull();
        assertThat(max1).isEqualTo(1);
        assertThat(max2).isEqualTo(2);
        assertThat(max3).isEqualTo(3);
        assertThat(max4).isEqualTo(4);
        assertThat(max5).isEqualTo(5);
    }

    @Test
    @DisplayName("Тест без удаления с числами 5, 4, 3, 2, 1")
    void testWithoutRemove54321() {
        StockMarket stockMarket = new StockMarket();

        Stock maxEmpty = stockMarket.mostValuableStock();
        stockMarket.add(new Stock(5));
        Integer max1 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(4));
        Integer max2 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(3));
        Integer max3 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(2));
        Integer max4 = stockMarket.mostValuableStock().getValue();
        stockMarket.add(new Stock(1));
        Integer max5 = stockMarket.mostValuableStock().getValue();

        assertThat(maxEmpty).isNull();
        assertThat(max1).isEqualTo(5);
        assertThat(max2).isEqualTo(5);
        assertThat(max3).isEqualTo(5);
        assertThat(max4).isEqualTo(5);
        assertThat(max5).isEqualTo(5);
    }

    @Test
    @DisplayName("Тест с удалениями")
    void testWithRemove() {
        StockMarket stockMarket = new StockMarket();

        final var stock1 = new Stock(1);
        final var stock2 = new Stock(2);
        final var stock3 = new Stock(3);
        final var stock4 = new Stock(4);
        final var stock5 = new Stock(5);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.remove(stock1);
        Integer max1 = stockMarket.mostValuableStock().getValue();
        stockMarket.remove(stock2);
        Stock max2 = stockMarket.mostValuableStock();
        stockMarket.add(stock3);
        stockMarket.add(stock4);
        stockMarket.add(stock5);
        stockMarket.remove(stock5);
        Integer max3 = stockMarket.mostValuableStock().getValue();
        stockMarket.remove(stock4);
        Integer max4 = stockMarket.mostValuableStock().getValue();
        stockMarket.remove(stock3);
        Stock max5 = stockMarket.mostValuableStock();

        assertThat(max1).isEqualTo(2);
        assertThat(max2).isNull();
        assertThat(max3).isEqualTo(4);
        assertThat(max4).isEqualTo(3);
        assertThat(max5).isNull();
    }
}
