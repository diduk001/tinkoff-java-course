package edu.hw3;

import java.util.ArrayList;

final public class Task2 {
    final static String UNBALANCED_SEQUENCE_MESSAGE = "Bracket sequence is not balanced";

    private Task2() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    static public ArrayList<String> clusterize(String bracketSequence) {
        ArrayList<String> result = new ArrayList<>();

        int bracketsBalance = 0;
        StringBuilder currentSequence = new StringBuilder();

        for (int i = 0; i < bracketSequence.length(); i++) {
            Character curBracket = bracketSequence.charAt(i);
            currentSequence.append(curBracket);

            if (curBracket.equals('(')) {
                bracketsBalance++;
                continue;
            }

            if (bracketsBalance == 0) {
                throw new IllegalArgumentException(UNBALANCED_SEQUENCE_MESSAGE);
            }
            bracketsBalance--;
            if (bracketsBalance == 0) {
                result.add(currentSequence.toString());
                currentSequence.setLength(0); // Clear StringBuilder
            }
        }

        if (bracketsBalance != 0) {
            throw new IllegalArgumentException(UNBALANCED_SEQUENCE_MESSAGE);
        }

        return result;

    }
}
