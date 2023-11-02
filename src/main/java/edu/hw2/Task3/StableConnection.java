package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Stable connection executes " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Stable connection is closed");
    }
}
