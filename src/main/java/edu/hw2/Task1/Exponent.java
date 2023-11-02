package edu.hw2.Task1;

public record Exponent(Evaluator base, int pow) implements Evaluator {
    @Override
    public double evaluate() {
        return Math.pow(base.evaluate(), pow);
    }
}
