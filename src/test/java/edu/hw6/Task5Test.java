package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @ParameterizedTest(name = "Заголовок новости https://news.ycombinator.com/item?id={0}")
    @ValueSource(longs = {38241304, 38257094, 38257794, 38256810, 38262315})
    void newsNameTest(long id) {
        final String result = HackerNews.news(id);
        final String expected = switch ((int) id) {
            case 38241304 -> "HTML First";
            case 38257094 -> "A coder considers the waning days of the craft";
            case 38257794 -> "Starship will attempt a launch this Friday";
            case 38256810 -> "Nepal bans TikTok and says it disrupts social harmony";
            case 38262315 -> "Blender 4.0 release notes";
            default -> throw new IllegalArgumentException("Unexpected case");
        };

        assertThat(result).isEqualTo(expected);
    }
}
