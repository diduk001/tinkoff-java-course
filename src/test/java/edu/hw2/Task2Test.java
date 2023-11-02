package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @Test
    @DisplayName("Проверка наследования Square от Rectangle")
    void inheritanceTest() {
        boolean inherits = Rectangle.class.isAssignableFrom(Square.class);
        assertThat(inherits).isTrue();
    }

    @ParameterizedTest(name = "Тест на изменение ширины и высоты")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        boolean changedBothHeight = rect.setWidth(20);
        boolean changedBothWidth = rect.setHeight(10);

        assertThat(changedBothHeight).isEqualTo(changedBothWidth);
        if (changedBothHeight) {
            assertThat(rect.area()).isEqualTo(100.0);
        } else {
            assertThat(rect.area()).isEqualTo(200.0);
        }
    }

    @ParameterizedTest(name = "Тест на отрицательные параметры")
    @MethodSource("rectangles")
    void negativeRectangleSide(Rectangle rect) {
        rect.setWidth(20);
        boolean changedBoth = rect.setHeight(10);

        rect.setWidth(-100);
        rect.setHeight(-20);

        if (changedBoth) {
            assertThat(rect.area()).isEqualTo(100.0);
        } else {
            assertThat(rect.area()).isEqualTo(200.0);
        }
    }
}
