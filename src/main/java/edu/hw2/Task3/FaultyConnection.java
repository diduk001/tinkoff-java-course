package edu.hw2.Task3;

public final class FaultyConnection implements Connection {
    private static final double THRESHOLD = 0.3;

    @Override
    public void execute(String command) throws ConnectionException {
        final double throwCheck = Math.random();
        if (throwCheck < THRESHOLD) {
            throw new ConnectionException("Can't execute command " + command);
        }
    }

    @Override
    public void close() throws ConnectionException {
        final double throwCheck = Math.random();
        if (throwCheck < THRESHOLD) {
            throw new ConnectionException("Can't close faulty connection");
        }
    }
}
