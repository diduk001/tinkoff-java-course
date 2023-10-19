package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Вызов метода наружнего класса")
    void outerClassTest() {
        var outerClassObject = new OuterClass();
        CallingInfo outerClassMethodInfo = outerClassObject.outerMethod();
        String className = outerClassMethodInfo.className();
        String methodName = outerClassMethodInfo.methodName();
        assertThat(className).isEqualTo("edu.hw2.Task4Test$OuterClass");
        assertThat(methodName).isEqualTo("outerMethod");
    }

    @Test
    @DisplayName("Вызов метода внутреннего класса")
    void innerClassTest() {
        var innerClassObject = new OuterClass.InnerClass();
        CallingInfo innerClassMethodInfo = innerClassObject.innerMethod();
        String className = innerClassMethodInfo.className();
        String methodName = innerClassMethodInfo.methodName();
        assertThat(className).isEqualTo("edu.hw2.Task4Test$OuterClass$InnerClass");
        assertThat(methodName).isEqualTo("innerMethod");
    }

    @Test
    @DisplayName("Вызов рекурсивного метода внешнего класса")
    void recursiveOuterClassTest() {
        var outerClassObject = new OuterClass();
        CallingInfo outerClassMethodInfo = outerClassObject.outerRecursiveMethod(5);
        String className = outerClassMethodInfo.className();
        String methodName = outerClassMethodInfo.methodName();
        assertThat(className).isEqualTo("edu.hw2.Task4Test$OuterClass");
        assertThat(methodName).isEqualTo("outerRecursiveMethod");
    }

    static final class OuterClass {
        CallingInfo outerMethod() {
            return Task4.callinginfo();
        }

        CallingInfo outerRecursiveMethod(int i) {
            if (i == 0) {
                return Task4.callinginfo();
            } else {
                return outerRecursiveMethod(i - 1);
            }
        }

        final static class InnerClass {
            CallingInfo innerMethod() {
                return Task4.callinginfo();
            }
        }
    }

}
