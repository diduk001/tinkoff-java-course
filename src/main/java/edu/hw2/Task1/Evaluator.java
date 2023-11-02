package edu.hw2.Task1;

public sealed interface Evaluator permits Constant, Addition, Exponent, Multiplication,
    Negate {
    double evaluate();
}
