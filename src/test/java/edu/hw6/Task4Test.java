package edu.hw6;

import edu.hw6.Task4.OutputStreamComposition;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path PATH = Path.of("src", "test", "java", "edu", "hw6", "TestFiles", "file.txt");

    @Test
    @DisplayName("Тест записи в файл")
    void fileWriteTest() {
        try {
            OutputStreamComposition.write(PATH, "File contents\n");
        } catch (IOException e) {
            LOGGER.error("Error while writing in file: " + e);
        }

        assertThat(Files.exists(PATH)).isTrue();
        try {
            String fileContents = Files.readString(PATH);
            assertThat(fileContents).isEqualTo("File contents\n");
        } catch (IOException e) {
            LOGGER.error("Error while reading file: " + e);
        }
    }

    @AfterEach
    void cleanTestFiles() {
        try {
            Files.deleteIfExists(PATH);
        } catch (IOException e) {
            LOGGER.error("Error while deleting files after tests: " + e);
        }
    }
}
