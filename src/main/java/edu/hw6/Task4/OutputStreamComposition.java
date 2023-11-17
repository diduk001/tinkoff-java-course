package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class OutputStreamComposition {
    private static final Logger LOGGER = LogManager.getLogger();

    private OutputStreamComposition() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        try {
            write(
                Path.of("src", "main", "java", "edu", "hw6", "Task4", "file.txt"),
                "Programming is learned by writing programs. ― Brian Kernighan"
            );
        } catch (IOException e) {
            LOGGER.error("Error while writing: " + e);
        }
    }

    public static OutputStream createOutputStream(Path path) throws IOException {
        return Files.newOutputStream(path);
    }

    public static CheckedOutputStream createCheckedOutputStream(Path path) throws IOException {
        OutputStream out = createOutputStream(path);
        return new CheckedOutputStream(out, new CRC32());
    }

    public static BufferedOutputStream createBufferedOutputStream(Path path) throws IOException {
        return new BufferedOutputStream(createCheckedOutputStream(path));
    }

    public static OutputStreamWriter createOutputStreamWriter(Path path) throws IOException {
        BufferedOutputStream buf = createBufferedOutputStream(path);
        return new OutputStreamWriter(buf, StandardCharsets.UTF_8);
    }

    public static PrintWriter createPrintWriter(Path path) throws IOException {
        return new PrintWriter(createOutputStreamWriter(path));
    }

    public static void write(Path path, String string) throws IOException {
        try (PrintWriter writer = createPrintWriter(path)) {
            writer.write(string);
        }
    }

}
