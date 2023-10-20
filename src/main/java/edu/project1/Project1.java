package edu.project1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public final class Project1 {
    private Project1() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        var game = new ConsoleHangman();
        game.run();
    }

    interface Wordlist {
        String getRandomWord();
    }

    sealed interface GuessResult {
        String message();

        record Defeat(String answer) implements GuessResult {
            public String message() {
                return "You lost! Answer was: " + answer;
            }
        }

        record Win(String answer) implements GuessResult {
            public String message() {
                return "You win! Answer: " + answer;
            }
        }

        final class SuccessfulGuess implements GuessResult {
            public String message() {
                return "Hit!";
            }
        }

        record FailedGuess(int curMistakes, int maxMistakes) implements GuessResult {
            public String message() {
                return "Missed! Mistake " + curMistakes + " out of " + maxMistakes;
            }
        }
    }

    final static class SimpleWordlist implements Wordlist {
        final private static String[] WORDS = {"person", "stream", "mighty", "prince", "snakes", "zombie", "circus"};

        public String getRandomWord() {
            int randomIdx = new Random().nextInt(WORDS.length);
            return WORDS[randomIdx];
        }
    }

    static class Session {
        private final String answer;
        private final char[] state;
        private final int maxMistakes;
        private int curMistakes;

        Session(Wordlist wordlist, int maxMistakes) {
            if (maxMistakes <= 0) {
                throw new IllegalArgumentException("maxMistakes can be only positive");
            }
            this.maxMistakes = maxMistakes;
            this.answer = wordlist.getRandomWord();
            if (this.answer.isEmpty()) {
                throw new IllegalArgumentException("getRandomWord returned empty string");
            }
            state = new char[this.answer.length()];
            Arrays.fill(state, '*');
            this.curMistakes = 0;
        }

        GuessResult tryGuess(final char guess) {
            boolean isSuccessful = false;
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) != guess) {
                    continue;
                }

                isSuccessful = true;
                state[i] = guess;
            }

            if (!isSuccessful) {
                curMistakes += 1;
                if (curMistakes == maxMistakes) {
                    return new GuessResult.Defeat(answer);
                }
                return new GuessResult.FailedGuess(curMistakes, maxMistakes);
            } else if (isGuessed()) {
                return new GuessResult.Win(answer);
            } else {
                return new GuessResult.SuccessfulGuess();
            }
        }

        private boolean isGuessed() {
            for (char c : state) {
                if (c == '*') {
                    return false;
                }
            }

            return true;
        }

        public boolean isPlayable() {
            return !isGuessed() && curMistakes < maxMistakes;
        }

        public String getState() {
            return new String(state);
        }

        public int getCurMistakes() {
            return curMistakes;
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    static final class ConsoleHangman {
        private final static char GIVE_UP_CHAR = '-';
        private final static int MAX_MISTAKES = 5;
        private final Session session;
        private final Scanner inputScanner;

        ConsoleHangman() {
            Wordlist wordlist = new SimpleWordlist();
            session = new Session(wordlist, MAX_MISTAKES);
            inputScanner = new Scanner(System.in);
        }

        public void run() {
            printWelcomeMessage();
            while (session.isPlayable()) {
                printSessionState();
                final char guess = inputLetter();
                if (guess == GIVE_UP_CHAR) {
                    System.out.println("You gave up :^(");
                    printGuessResult(new GuessResult.Defeat(session.answer));
                    break;
                }

                var guessResult = session.tryGuess(guess);
                printGuessResult(guessResult);
            }
        }

        private char inputLetter() {
            while (true) {
                System.out.println("Guess a letter: ");
                System.out.print("> ");
                final String userInput = inputScanner.nextLine();
                if (userInput.length() != 1) {
                    System.out.println("Input only one character");
                    continue;
                }

                final char userInputChar = userInput.charAt(0);
                if (Character.isLetter(userInputChar)) {
                    return Character.toLowerCase(userInputChar);
                } else if (userInputChar == GIVE_UP_CHAR) {
                    return GIVE_UP_CHAR;
                } else {
                    System.out.println("Input one letter or '" + GIVE_UP_CHAR + "' to give up");
                }

            }
        }

        private void printSessionState() {
            final String currentState = session.getState();
            System.out.println("Current state: " + currentState);
        }

        private void printGuessResult(GuessResult result) {
            System.out.println(result.message());
        }

        private void printWelcomeMessage() {
            System.out.println("Welcome to hangman!");
            System.out.println("I will set a word and you'll guess letters");
            System.out.println("Type '" + GIVE_UP_CHAR + "' if you want to give up");
            System.out.println("Good luck!");
            System.out.println();
        }
    }
}
