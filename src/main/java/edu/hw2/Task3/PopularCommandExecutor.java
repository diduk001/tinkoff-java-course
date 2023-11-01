package edu.hw2.Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(final ConnectionManager manager, final int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws ConnectionException {
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
