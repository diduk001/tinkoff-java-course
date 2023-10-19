package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    private final int ITERATIONS = 50;

    @Test
    @DisplayName("Тест стабильного соединения")
    void stableConnectionTest() {
        Task3.Connection conn = new Task3.StableConnection();
        int exceptionCounter = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                conn.execute("Command #" + i);
            } catch (Task3.ConnectionException e) {
                exceptionCounter++;
            }
        }
        conn.close();

        assertThat(exceptionCounter).isZero();
    }

    @Test
    @DisplayName("Тест падающего соединения")
    void faultyConnectionTest() {
        Task3.Connection conn = new Task3.FaultyConnection();
        int exceptionCounter = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                conn.execute("Command #" + i);
            } catch (Task3.ConnectionException ignored) {
                exceptionCounter++;
            }
        }
        conn.close();

        assertThat(exceptionCounter).isNotZero();
    }

    @Test
    @DisplayName("Тест возвращаемых соединений DefaultConnectionManager")
    void defaultConnectionManagerTest() {
        final var connectionManager = new Task3.DefaultConnectionManager();
        int faultyConnectionCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Task3.Connection conn = connectionManager.getConnection();
            if (conn instanceof Task3.FaultyConnection) {
                faultyConnectionCount++;
            }
            conn.close();
        }

        assertThat(faultyConnectionCount).isNotZero();
    }

    @Test
    @DisplayName("Тест возвращаемых соединений FaultyConnectionManager")
    void faultyConnectionManagerTest() {
        final var connectionManager = new Task3.FaultyConnectionManager();
        int stableConnectionCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Task3.Connection conn = connectionManager.getConnection();
            if (conn instanceof Task3.StableConnection) {
                stableConnectionCount++;
            }
            conn.close();
        }

        assertThat(stableConnectionCount).isZero();
    }

    @Test
    @DisplayName("Тест работы метода tryExecute с FaultyConnectionManager")
    void tryExecuteFaultyConnectionTest() {
        final var manager = new Task3.FaultyConnectionManager();
        final var executor = new Task3.PopularCommandExecutor(manager, ITERATIONS);
        boolean wasCatched = false;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                executor.updatePackages();
            } catch (Task3.ConnectionException e) {
                wasCatched = true;
                break;
            }
        }

        assertThat(wasCatched).isTrue();
    }

    @Test
    @DisplayName("Тест работы метода tryExecute с DefaultConnectionManager")
    void tryExecuteDefaultConnectionTest() {
        final var manager = new Task3.DefaultConnectionManager();
        final var executor = new Task3.PopularCommandExecutor(manager, ITERATIONS);
        boolean wasSuccessful = false;
        for (int i = 0; i < ITERATIONS; i++) {
            try {
                executor.tryExecute("Example command");
                wasSuccessful = true;
                break;
            } catch (Task3.ConnectionException ignored) {
            }
        }
        assertThat(wasSuccessful).isTrue();
    }

}
