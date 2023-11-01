package edu.hw2;

import edu.hw2.Task1.Evaluator.Addition;
import edu.hw2.Task1.Evaluator.Constant;
import edu.hw2.Task1.Evaluator.Exponent;
import edu.hw2.Task1.Evaluator.Multiplication;
import edu.hw2.Task1.Evaluator.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Пример из условия")
    void sampleTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1)); // -1
        var sumTwoFour = new Addition(two, four); // 2 + 4 = 6
        var mult = new Multiplication(sumTwoFour, negOne); // 6 * (-1) = -6
        var exp = new Exponent(mult, 2); // 6 ^ 2 = 36
        var res = new Addition(exp, new Constant(1)); // 6 + 1 = 37
        assertThat(res.evaluate()).isEqualTo(37);
    }

    @ParameterizedTest(name = "Для double: Отрицательный куб суммы произведения {0}*({0}-10) и 17")
    @ValueSource(ints = {-100, -80, -33, 0, 10, 99, 10010})
    void allOperationsIntTest(int val) {
        final var valConst = new Constant(val);
        final var minus10Const = new Negate(new Constant(10));
        final var valMinus10 = new Addition(valConst, minus10Const);
        final var seventeenConst = new Constant(17);

        final var prod = new Multiplication(valConst, valMinus10);
        final var sum = new Addition(prod, seventeenConst);
        final var cubed = new Exponent(sum, 3);
        final var negated = new Negate(cubed);

        final double evaluated = negated.evaluate();
        final double correct = -Math.pow(((val * (val - 10)) + 17), 3);
        assertThat(evaluated).isEqualTo(correct);
    }

    @ParameterizedTest(name = "Для double: Отрицательный куб суммы произведения {0}*({0}-1.4) и 22.14")
    @ValueSource(doubles = {-121.01, -81.3331, -32.89123, 0.0, 10.111213, 99.0000123, 1009.443})
    void allOperationsDoubleTest(double val) {
        final var valConst = new Constant(val);
        final var minusOnePointFourConst = new Negate(new Constant(1.4));
        final var valMinusOnePointFour = new Addition(valConst, minusOnePointFourConst);
        final var twentyTwoPointFourteen = new Constant(22.14);

        final var prod = new Multiplication(valConst, valMinusOnePointFour);
        final var sum = new Addition(prod, twentyTwoPointFourteen);
        final var cubed = new Exponent(sum, 3);
        final var negated = new Negate(cubed);

        final double evaluated = negated.evaluate();
        final double correct = -Math.pow(((val * (val - 1.4)) + 22.14), 3);
        assertThat(evaluated).isEqualTo(correct);
    }
}
