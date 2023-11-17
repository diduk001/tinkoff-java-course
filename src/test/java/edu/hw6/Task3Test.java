package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import edu.hw6.Task3.Filters;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Path TEST_FILES_PATH = Path.of("src", "test", "java", "edu", "hw6", "Task3TestFiles");
    private static final Path SOME_DIR_PATH = TEST_FILES_PATH.resolve("SomeDir");
    private static final Path DUKE_PNG_PATH = TEST_FILES_PATH.resolve("Duke_(Java_mascot)_waving.svg.png");
    private static final Path JAVA_PNG_PATH = TEST_FILES_PATH.resolve("Java_programming_language_logo.svg.png");
    private static final Path SOME_TEXT_FILE_PATH = TEST_FILES_PATH.resolve("SomeTextFile.txt");

    @Test
    @DisplayName("Тест фильтра для файлов")
    void regularFileFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.regularFile;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(3)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while regular file filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для директорий")
    void directoryFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.directory;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(SOME_DIR_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while directory filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для доступных для прочтения")
    void readableFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.readable;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(4)
                .contains(SOME_DIR_PATH)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while readable filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для доступных для записи")
    void writeableFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.writable;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(4)
                .contains(SOME_DIR_PATH)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while writeable filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра больших чем заданный размер")
    void largerThanFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.largerThan(72_000);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(2)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while larger than filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для равных по размеру")
    void equalSizeFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.sizeIsEqualTo(73_784);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(DUKE_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while equal size filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для меньших чем заданный размер")
    void smallerThanFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.regularFile.and(Filters.smallerThan(2_000));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while smaller than filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для расширения")
    void extensionFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.extensionIs("txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while extension filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для соответствия glob-шаблону")
    void globFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.globMatches("*t*");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(2)
                .contains(DUKE_PNG_PATH)
                .contains(SOME_TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while glob matching filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для соответствия Pattern")
    void patternFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.matchesPattern(Pattern.compile(".*?Java.*"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(2)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while pattern matching filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест фильтра для соответствия по magic number")
    void magicNumberFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters.magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G');

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(2)
                .contains(DUKE_PNG_PATH)
                .contains(JAVA_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while readable filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест сцепления фильтров")
    void chainingFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters
            .regularFile
            .and(Filters.smallerThan(75_000))
            .and(Filters.largerThan(1_000))
            .and(Filters.globMatches("*p*.png"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(JAVA_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while readable filter test: " + e);
        }
    }

    @Test
    @DisplayName("Тест отрицания фильтров")
    void negatingFilterTest() {
        DirectoryStream.Filter<Path> filter = Filters
            .regularFile
            .and(AbstractFilter.not(Filters.extensionIs("txt")))
            .and(AbstractFilter.not(Filters.globMatches("Duke*")));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            ArrayList<Path> result = new ArrayList<>();
            entries.forEach(result::add);
            assertThat(result)
                .hasSize(1)
                .contains(JAVA_PNG_PATH);
        } catch (IOException e) {
            LOGGER.error("Error while readable filter test: " + e);
        }
    }
}
