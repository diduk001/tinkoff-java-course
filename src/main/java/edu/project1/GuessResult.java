package edu.project1;

// Интерфейс результата одной попытки
public sealed interface GuessResult {
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
