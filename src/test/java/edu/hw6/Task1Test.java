package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path FILES_PATH = Path.of("src", "test", "java", "edu", "hw6", "TestFiles");

    @Test
    @DisplayName("Проверка корректности дополнения")
    void checkAppendingCorrectness() {
        final Path pathToWrite = FILES_PATH.resolve("diskmap.txt");
        final DiskMap result = DiskMap.ofMap(Map.of("Key 1", "Value 1", "Key 2", "Value 2"));
        final DiskMap expected = DiskMap.ofMap(Map.of("Key 1", "Value 1", "Key 2", "Value 2", "Key 3", "Value 3"));

        try {
            Files.createFile(pathToWrite);
            Files.writeString(pathToWrite, "Key 3:Value 3");
        } catch (IOException e) {
            LOGGER.error("Error while writing in file: " + e);
        }

        try {
            result.appendFile(pathToWrite);
            assertThat(result.equals(expected)).isTrue();
        } catch (IOException e) {
            LOGGER.error("Error while opening file: " + e);
        }
    }

    @Test
    @DisplayName("Проверка корректности считывания")
    void checkOpeningCorrectness() {
        final Path pathToWrite = FILES_PATH.resolve("diskmap.txt");
        final DiskMap expected = DiskMap.ofMap(Map.of("Key 1", "Value 1", "Key 2", "Value 2"));

        try {
            Files.createFile(pathToWrite);
            Files.writeString(pathToWrite, "Key 1:Value 1\nKey 2:Value 2");
        } catch (IOException e) {
            LOGGER.error("Error while writing in file: " + e);
        }

        try {
            final DiskMap result = DiskMap.open(pathToWrite);
            assertThat(result.equals(expected)).isTrue();
        } catch (IOException e) {
            LOGGER.error("Error while opening file: " + e);
        }
    }

    @Test
    @DisplayName("Проверка равенства с сохранённым в файл DiskMap'ом")
    void checkEqualityWithSavedAsFile() {
        DiskMap expected = new DiskMap();
        expected.put("Key 1", "Value 1");
        expected.put("Key 2", "Value 2");
        expected.put("Key 3", "Value 3");
        try {
            expected.save(FILES_PATH.resolve("diskmap.txt"));
        } catch (IOException e) {
            LOGGER.error("Error while saving: " + e);
            return;
        }

        assertThat(Files.exists(FILES_PATH.resolve("diskmap.txt"))).isTrue();
        try {
            DiskMap result = DiskMap.open(FILES_PATH.resolve("diskmap.txt"));
            assertThat(result.equals(expected)).isTrue();
        } catch (IOException e) {
            LOGGER.error("Error while opening: " + e);
        }
    }

    @AfterEach
    void cleanTestFiles() {
        try (var stream = Files.walk(FILES_PATH)) {
            final List<Path> toDelete = stream.toList();
            for (Path path : toDelete) {
                if (!Files.exists(path) || path.equals(FILES_PATH)) {
                    continue;
                }
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            LOGGER.error("Error while deleting files after tests: " + e);
        }
    }
}
