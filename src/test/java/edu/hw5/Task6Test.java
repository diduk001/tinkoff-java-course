package edu.hw5;

import edu.hw5.Task6.SubsequenceChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Пример из условия")
    void sampleTest() {
        final boolean result = SubsequenceChecker.isSubstring("abc", "achfdbaabgabcaabg");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Пустое S")
    void emptySTest() {
        final boolean result = SubsequenceChecker.isSubstring("", "abc");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Пустое T")
    void emptyTTest() {
        final boolean result = SubsequenceChecker.isSubstring("abc", "");
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Пустое S и T")
    void emptySandTTest() {
        final boolean result = SubsequenceChecker.isSubstring("", "");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Одинаковые строки")
    void equalStrings() {
        final boolean result = SubsequenceChecker.isSubstring("abc", "abc");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Тест на подпоследовательности")
    void subsequenceTest() {
        final boolean result = SubsequenceChecker.isSubstring("bd", "abcd");
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Большой ест на подпоследовательности")
    void bigSubsequenceTest() {
        final boolean result = SubsequenceChecker.isSubstring("test", "sttthhhellttsssalt");
        assertThat(result).isTrue();
    }
}
