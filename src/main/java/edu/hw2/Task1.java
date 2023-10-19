package edu.hw2;

public sealed interface Task1 {
    double evaluate();

    record Constant(double val) implements Task1 {
        @Override
        public double evaluate() {
            return val;
        }
    }

    record Negate(Task1 expr) implements Task1 {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }
    }

    record Exponent(Task1 base, int pow) implements Task1 {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), pow);
        }
    }

    record Addition(Task1 a, Task1 b) implements Task1 {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    record Multiplication(Task1 a, Task1 b) implements Task1 {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}
