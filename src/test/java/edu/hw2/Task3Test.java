package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    private final int ITERATIONS = 50;

    @Test
    @DisplayName("Тест стабильного соединения")
    void stableConnectionTest() {
        Connection conn = new StableConnection();
        int exceptionCounter = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                conn.execute("Command #" + i);
            } catch (ConnectionException e) {
                exceptionCounter++;
            }
        }
        conn.close();

        assertThat(exceptionCounter).isZero();
    }

    @Test
    @DisplayName("Тест падающего соединения")
    void faultyConnectionTest() {
        int exceptionCounter = 0;

        try (Connection conn = new FaultyConnection()) {
            for (int i = 0; i < ITERATIONS; i++) {
                conn.execute("Command #" + i);
            }

        } catch (ConnectionException ignored) { // Faulty connection case
            exceptionCounter++;
        }

        assertThat(exceptionCounter).isNotZero();
    }

    @Test
    @DisplayName("Тест возвращаемых соединений DefaultConnectionManager")
    void defaultConnectionManagerTest() {
        final var connectionManager = new DefaultConnectionManager();
        int faultyConnectionCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Connection conn = connectionManager.getConnection();
            if (conn instanceof FaultyConnection) {
                faultyConnectionCount++;
            }
            try {
                conn.close();
            } catch (ConnectionException ignored) { // Faulty connection case
            }
        }

        assertThat(faultyConnectionCount).isNotZero();
    }

    @Test
    @DisplayName("Тест возвращаемых соединений FaultyConnectionManager")
    void faultyConnectionManagerTest() {
        final var connectionManager = new FaultyConnectionManager();
        int stableConnectionCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Connection conn = connectionManager.getConnection();
            if (conn instanceof StableConnection) {
                stableConnectionCount++;
            }
            try {
                conn.close();
            } catch (ConnectionException ignored) {
            }
        }

        assertThat(stableConnectionCount).isZero();
    }

    @Test
    @DisplayName("Тест работы метода tryExecute с FaultyConnectionManager")
    void tryExecuteFaultyConnectionTest() {
        final var manager = new FaultyConnectionManager();
        final var executor = new PopularCommandExecutor(manager, ITERATIONS);
        boolean wasCatched = false;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                executor.updatePackages();
            } catch (ConnectionException e) {
                wasCatched = true;
                break;
            }
        }

        assertThat(wasCatched).isTrue();
    }

    @Test
    @DisplayName("Тест работы метода tryExecute с DefaultConnectionManager")
    void tryExecuteDefaultConnectionTest() {
        final var manager = new DefaultConnectionManager();
        final var executor = new PopularCommandExecutor(manager, ITERATIONS);
        boolean wasSuccessful = false;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                executor.tryExecute("Example command");
                wasSuccessful = true;
                break;
            } catch (ConnectionException ignored) {
            }
        }
        assertThat(wasSuccessful).isTrue();
    }

}
