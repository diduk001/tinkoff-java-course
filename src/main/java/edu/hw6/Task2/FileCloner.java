package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileCloner {
    static final String TO_APPEND = " — копия";
    static final Pattern FIRST_COPY_PATTERN = Pattern.compile("^.*— копия$");
    static final Pattern NEXT_COPY_PATTERN = Pattern.compile("^.*— копия \\((\\d+)\\)$");
    static final String EXTENSION_SEPARATOR_PATTERN = "\\.";
    private static final Logger LOGGER = LogManager.getLogger();

    private FileCloner() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static String removeLastExtension(String name) {
        final String[] split = name.split(EXTENSION_SEPARATOR_PATTERN);
        if (split.length == 1) {
            return name;
        }

        final String[] withoutLastExtension = Arrays.copyOfRange(split, 0, split.length - 1);
        return String.join(".", withoutLastExtension);
    }

    public static String getExtension(String name) {
        final String[] split = name.split(EXTENSION_SEPARATOR_PATTERN);
        if (split.length == 1) {
            return "";
        }
        return split[split.length - 1];
    }

    public static String getFirstCopy(String name) {
        final String withoutExtension = removeLastExtension(name);
        final String extension = getExtension(name);
        return extension.isEmpty() ? withoutExtension + TO_APPEND : withoutExtension + TO_APPEND + "." + extension;
    }

    public static String getNextCopy(String name) {
        final String withoutExtension = removeLastExtension(name);
        final String extension = getExtension(name);
        if (FIRST_COPY_PATTERN.matcher(withoutExtension).matches()) {
            final String newWithoutExtension = withoutExtension + " (1)";
            return extension.isEmpty() ? newWithoutExtension : newWithoutExtension + '.' + extension;
        } else {
            final Matcher matcher = NEXT_COPY_PATTERN.matcher(withoutExtension);
            LOGGER.info("Without extension: " + withoutExtension);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Filename must end with \"" + TO_APPEND + "\"");
            }

            int copyNumber = Integer.parseInt(matcher.group(1));
            copyNumber += 1;
            final String newWithoutExtension = new StringBuilder(withoutExtension).replace(
                matcher.start(1),
                matcher.end(1),
                String.valueOf(copyNumber)
            ).toString();

            return extension.isEmpty() ? newWithoutExtension : newWithoutExtension + '.' + extension;
        }
    }

    public static Path findNonExistentCopyPath(Path path) {
        final String filename = path.getFileName().toString();
        String copyFilename = getFirstCopy(filename);
        while (Files.exists(path.resolveSibling(copyFilename))) {
            copyFilename = getNextCopy(copyFilename);
        }
        return path.resolveSibling(copyFilename);
    }

    public static void cloneFile(Path path) {
        byte[] bytes;

        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            LOGGER.error("Error while reading file: " + e);
            return;
        }
        Path newPath = findNonExistentCopyPath(path);
        try {
            Files.createFile(newPath);
        } catch (IOException e) {
            LOGGER.error("Error while creating file: " + e);
        }
        try {

            Files.write(newPath, bytes);
        } catch (IOException e) {
            LOGGER.error("Error while writing in file: " + e);
        }
    }
}
