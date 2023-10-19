package edu.hw2;

public class Task3 {

    public interface Connection extends AutoCloseable {
        default void execute(String command) {
        }

        default void close() {
        }
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    static public class ConnectionException extends RuntimeException {
    }

    static final public class StableConnection implements Connection {
    }

    static final public class FaultyConnection implements Connection {
        final private static double THRESHOLD = 0.3;

        @Override
        public void execute(String command) throws ConnectionException {
            final double throwCheck = Math.random();
            if (throwCheck < THRESHOLD) {
                throw new ConnectionException();
            }
        }
    }

    public static final class DefaultConnectionManager implements ConnectionManager {
        final private static double THRESHOLD = 0.3;

        public Connection getConnection() {
            final double faultyCheck = Math.random();
            if (faultyCheck < THRESHOLD) {
                return new FaultyConnection();
            } else {
                return new StableConnection();
            }

        }
    }

    public static final class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor(final ConnectionManager manager, final int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() throws ConnectionException {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) throws ConnectionException {
            final var connection = manager.getConnection();
            for (int attemptIdx = 0; attemptIdx < this.maxAttempts; attemptIdx++) {
                try {
                    connection.execute(command);
                } catch (ConnectionException cause) {
                    if (attemptIdx == this.maxAttempts - 1) {
                        connection.close();
                        throw cause;
                    }
                }
            }
            connection.close();
        }
    }
}
