package edu.hw2.Task1;

public record Addition(Evaluator a, Evaluator b) implements Evaluator {
    @Override
    public double evaluate() {
        return a.evaluate() + b.evaluate();
    }
}
