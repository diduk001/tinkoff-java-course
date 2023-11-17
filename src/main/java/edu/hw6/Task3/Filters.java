package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Filters {
    private static final Logger LOGGER = LogManager.getLogger();
    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter directory = Files::isDirectory;
    public static AbstractFilter readable = Files::isReadable;
    public static AbstractFilter writable = Files::isWritable;

    private Filters() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static DirectoryStream.Filter<Path> largerThan(int sizeBytes) {
        return (AbstractFilter) entry -> {
            try {
                return Files.size(entry) > sizeBytes;
            } catch (IOException e) {
                LOGGER.error("Error while filtering larger than: " + e);
            }
            return true;
        };
    }

    public static DirectoryStream.Filter<Path> sizeIsEqualTo(int sizeBytes) {
        return (AbstractFilter) entry -> {
            try {
                return Files.size(entry) == sizeBytes;
            } catch (IOException e) {
                LOGGER.error("Error while filtering equal size: " + e);
            }
            return true;
        };
    }

    public static DirectoryStream.Filter<Path> smallerThan(int sizeBytes) {
        return (AbstractFilter) entry -> {
            try {
                return Files.size(entry) < sizeBytes;
            } catch (IOException e) {
                LOGGER.error("Error while filtering smaller than: " + e);
            }
            return true;
        };
    }

    public static DirectoryStream.Filter<Path> extensionIs(final String extension) {
        return (AbstractFilter) entry -> entry.toString().endsWith("." + extension);
    }

    public static DirectoryStream.Filter<Path> globMatches(final String pattern) {
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        return (AbstractFilter) entry -> matcher.matches(entry.getFileName());
    }

    public static DirectoryStream.Filter<Path> matchesPattern(final Pattern pattern) {
        return (AbstractFilter) entry -> pattern.matcher(entry.getFileName().toString()).matches();
    }

    public static DirectoryStream.Filter<Path> magicNumber(byte... magicNumber) {
        return (AbstractFilter) entry -> {
            try {
                if (!Files.isRegularFile(entry)) {
                    return false;
                }

                byte[] bytes = Files.readAllBytes(entry);
                if (magicNumber.length > bytes.length) {
                    return false;
                }
                boolean matches = true;
                for (int i = 0; i < magicNumber.length; i++) {
                    if (magicNumber[i] != bytes[i]) {
                        matches = false;
                        break;
                    }
                }
                return matches;
            } catch (IOException e) {
                LOGGER.error("Error while filtering by magic number: " + e);
            }
            return true;
        };
    }
}
