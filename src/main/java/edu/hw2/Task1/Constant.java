package edu.hw2.Task1;

public record Constant(double val) implements Evaluator {
    @Override
    public double evaluate() {
        return val;
    }
}
