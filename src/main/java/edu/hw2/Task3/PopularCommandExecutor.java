package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private static final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.info("Attempt #" + attemptIdx);
            try {
                connection.execute(command);
            } catch (ConnectionException cause) {
                if (attemptIdx == this.maxAttempts - 1) {
                    connection.close();
                    LOGGER.error("Too many attempts");
                    throw cause;
                }
            }
        }
        connection.close();
    }
}
