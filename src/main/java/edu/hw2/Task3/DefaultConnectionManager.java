package edu.hw2.Task3;

public final class DefaultConnectionManager implements ConnectionManager {
    private static final double THRESHOLD = 0.3;

    public Connection getConnection() {
        final double faultyCheck = Math.random();
        if (faultyCheck < THRESHOLD) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }

    }
}
