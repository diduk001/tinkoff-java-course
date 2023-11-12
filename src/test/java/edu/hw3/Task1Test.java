package edu.hw3;

import edu.hw3.Task1.AtbashEncrypt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @ParameterizedTest(name = "Тест из условия #{index} - \"{0}\"")
    @ValueSource(strings = {"Hello world!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"})
    void sampleTest(String plaintext) {
        final String result = AtbashEncrypt.atbash(plaintext);
        final String expected = switch (plaintext) {
            case "Hello world!" -> "Svool dliow!";
            case "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler" ->
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
            default -> ""; // Unexpected
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Англйиский алфавит ${index}")
    @ValueSource(strings = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"})
    void alphabetTest(String plaintext) {
        final String result = AtbashEncrypt.atbash(plaintext);
        final String expected = switch (plaintext.charAt(0)) {
            case 'a' -> "zyxwvutsrqponmlkjihgfedcba";
            case 'A' -> "ZYXWVUTSRQPONMLKJIHGFEDCBA";
            default -> ""; // Unexpected
        };
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Строка остаётся без изменений - \"{0}\"")
    @ValueSource(strings = {"", "    ", ".,123", "-_0987\t{}[]", "!@#$4%5^6&*()=", "'\"<>|"})
    void stringWithoutLettersTest(String plaintext) {
        final String result = AtbashEncrypt.atbash(plaintext);
        assertThat(result).isEqualTo(plaintext);
    }
}
