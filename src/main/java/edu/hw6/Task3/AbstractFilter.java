package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    static AbstractFilter not(DirectoryStream.Filter<Path> filter) {
        return entry -> !filter.accept(entry);
    }

    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(DirectoryStream.Filter<Path> other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }
}
