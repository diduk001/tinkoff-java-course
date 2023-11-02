package edu.hw2.Task1;

public record Negate(Evaluator expr) implements Evaluator {
    @Override
    public double evaluate() {
        return -expr.evaluate();
    }
}
