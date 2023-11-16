package edu.hw6;

import edu.hw6.Task2.FileCloner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path FILES_PATH = Path.of("src", "test", "java", "edu", "hw6", "TestFiles");

    @NotNull private static Path cloneFilesOnceGetExpectedPath(String filename) {
        final String expectedCloneFilename = switch (filename) {
            case "Tinkoff Bank Biggest Secret.txt" -> "Tinkoff Bank Biggest Secret — копия.txt";
            case "Tinkoff Bank Biggest Secret — копия.txt" -> "Tinkoff Bank Biggest Secret — копия — копия.txt";
            case "FilenameWithoutExtension" -> "FilenameWithoutExtension — копия";
            case "FileNameWithThreeExtensions.png.zip.exe" -> "FileNameWithThreeExtensions.png.zip — копия.exe";
            default -> throw new IllegalArgumentException("Not supported argument");
        };

        return FILES_PATH.resolve(expectedCloneFilename);
    }

    @NotNull private static Path cloneFilesTwiceGetExpectedPath(String filename) {
        final String expectedCloneFilename = switch (filename) {
            case "Tinkoff Bank Biggest Secret.txt" -> "Tinkoff Bank Biggest Secret — копия (1).txt";
            case "Tinkoff Bank Biggest Secret — копия.txt" -> "Tinkoff Bank Biggest Secret — копия — копия (1).txt";
            case "FilenameWithoutExtension" -> "FilenameWithoutExtension — копия (1)";
            case "FileNameWithThreeExtensions.png.zip.exe" -> "FileNameWithThreeExtensions.png.zip — копия (1).exe";
            default -> throw new IllegalArgumentException("Not supported argument");
        };

        return FILES_PATH.resolve(expectedCloneFilename);
    }

    @ParameterizedTest(name = "Тест функции getFirstCopy: {0}")
    @ValueSource(strings = {
        "Tinkoff Bank Biggest Secret.txt",
        "Tinkoff Bank Biggest Secret — копия.txt",
        "FileNameWithoutExtension",
        "FileNameWithThreeExtensions.png.zip.exe"})
    void getFirstCopyTest(String name) {
        final String result = FileCloner.getFirstCopy(name);
        final String expected = switch (name) {
            case "Tinkoff Bank Biggest Secret.txt" -> "Tinkoff Bank Biggest Secret — копия.txt";
            case "Tinkoff Bank Biggest Secret — копия.txt" -> "Tinkoff Bank Biggest Secret — копия — копия.txt";
            case "FileNameWithoutExtension" -> "FileNameWithoutExtension — копия";
            case "FileNameWithThreeExtensions.png.zip.exe" -> "FileNameWithThreeExtensions.png.zip — копия.exe";
            default -> throw new IllegalArgumentException("Not supported argument");
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Тест функции getNextCopy: {0}")
    @ValueSource(strings = {
        "Tinkoff Bank Biggest Secret — копия.txt",
        "Tinkoff Bank Biggest Secret — копия (1).txt",
        "Tinkoff Bank Biggest Secret — копия (2).txt",
        "Tinkoff Bank Biggest Secret — копия (3).txt",
        "FileNameWithoutExtension — копия",
        "FileNameWithThreeExtensions.png.zip — копия.exe"})
    void getNextCopyTest(String name) {
        final String result = FileCloner.getNextCopy(name);
        final String expected = switch (name) {
            case "Tinkoff Bank Biggest Secret — копия.txt" -> "Tinkoff Bank Biggest Secret — копия (1).txt";
            case "Tinkoff Bank Biggest Secret — копия (1).txt" -> "Tinkoff Bank Biggest Secret — копия (2).txt";
            case "Tinkoff Bank Biggest Secret — копия (2).txt" -> "Tinkoff Bank Biggest Secret — копия (3).txt";
            case "Tinkoff Bank Biggest Secret — копия (3).txt" -> "Tinkoff Bank Biggest Secret — копия (4).txt";
            case "FileNameWithoutExtension — копия" -> "FileNameWithoutExtension — копия (1)";
            case "FileNameWithThreeExtensions.png.zip — копия.exe" ->
                "FileNameWithThreeExtensions.png.zip — копия (1).exe";
            default -> throw new IllegalArgumentException("Not supported argument");
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Однократное клонирование файла с названием \"{0}\"")
    @ValueSource(strings = {
        "Tinkoff Bank Biggest Secret.txt",
        "Tinkoff Bank Biggest Secret — копия.txt",
        "FilenameWithoutExtension",
        "FileNameWithThreeExtensions.png.zip.exe"
    })
    void cloneFileOnceTest(String filename) {
        final Path pathToClone = FILES_PATH.resolve(filename);
        try {
            Files.writeString(pathToClone, "File Content");
        } catch (IOException e) {
            LOGGER.error("Error while creating file " + filename + " : " + e);
            return;
        }

        FileCloner.cloneFile(pathToClone);

        final Path expectedClonePath = cloneFilesOnceGetExpectedPath(filename);
        assertThat(Files.exists(expectedClonePath)).isTrue();

        try {
            final String resultContent = Files.readString(expectedClonePath);
            assertThat(resultContent).isEqualTo("File Content");
        } catch (IOException e) {
            LOGGER.error("Error while reading cloned file: " + e);
        }
    }

    @ParameterizedTest(name = "Двухкратное клонирование файла с названием \"{0}\"")
    @ValueSource(strings = {
        "Tinkoff Bank Biggest Secret.txt",
        "Tinkoff Bank Biggest Secret — копия.txt",
        "FilenameWithoutExtension",
        "FileNameWithThreeExtensions.png.zip.exe"
    })
    void cloneFileTwiceTest(String filename) {
        final Path pathToClone = FILES_PATH.resolve(filename);
        try {
            Files.writeString(pathToClone, "File Content");
        } catch (IOException e) {
            LOGGER.error("Error while creating file " + filename + " : " + e);
            return;
        }

        FileCloner.cloneFile(pathToClone);
        final Path expectedClonePath = cloneFilesOnceGetExpectedPath(filename);
        assertThat(Files.exists(expectedClonePath)).isTrue();

        try {
            final String resultContent = Files.readString(expectedClonePath);
            assertThat(resultContent).isEqualTo("File Content");
        } catch (IOException e) {
            LOGGER.error("Error while reading cloned file: " + e);
        }

        FileCloner.cloneFile(pathToClone);
        final Path expectedClonePath2 = cloneFilesTwiceGetExpectedPath(filename);
        assertThat(Files.exists(expectedClonePath2)).isTrue();

        try {
            final String resultContent = Files.readString(expectedClonePath2);
            assertThat(resultContent).isEqualTo("File Content");
        } catch (IOException e) {
            LOGGER.error("Error while reading cloned file: " + e);
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
