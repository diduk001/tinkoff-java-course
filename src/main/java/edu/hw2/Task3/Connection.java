package edu.hw2.Task3;

public interface Connection extends AutoCloseable {
    default void execute(String command) {
    }

    default void close() {
    }
}
