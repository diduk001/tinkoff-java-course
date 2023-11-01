package edu.hw2.Task1;

public sealed interface Evaluator {
    double evaluate();

    record Constant(double val) implements Evaluator {
        @Override
        public double evaluate() {
            return val;
        }
    }

    record Negate(Evaluator expr) implements Evaluator {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }
    }

    record Exponent(Evaluator base, int pow) implements Evaluator {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), pow);
        }
    }

    record Addition(Evaluator a, Evaluator b) implements Evaluator {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    record Multiplication(Evaluator a, Evaluator b) implements Evaluator {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}
