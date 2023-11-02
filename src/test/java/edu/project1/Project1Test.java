package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project1Test {
    final Wordlist specificWordWordlist = () -> "specific"; // Словарь, состоящий из одного слова "specific"

    @Test
    @DisplayName("Неположительное максимальное кол-во ошибок при создании игровой сессии")
    void notPositiveMaxMistakes() {
        boolean exceptionOnZero = false;
        boolean exceptionOnNegative = false;

        try {
            new HangmanSession(new SimpleWordlist(), 0);
        } catch (Exception e) {
            exceptionOnZero = true;
        }

        try {
            new HangmanSession(new SimpleWordlist(), -100);
        } catch (Exception e) {
            exceptionOnNegative = true;
        }

        assertThat(exceptionOnZero).isTrue();
        assertThat(exceptionOnNegative).isTrue();
    }

    @Test
    @DisplayName("Словарь с пустой строкой при создании сессии")
    void emptyAnswerSession() {
        boolean exceptionOnEmpty = false;
        var emptyWordWordlist = new Wordlist() { // Словарь, выдающий пустую строку
            @Override
            public String getRandomWord() {
                return "";
            }
        };

        try {
            new HangmanSession(emptyWordWordlist, 5);
        } catch (Exception e) {
            exceptionOnEmpty = true;
        }

        assertThat(exceptionOnEmpty).isTrue();
    }

    @Test
    @DisplayName("Увеличение числа ошибок в сессии, если буквы нет в слове")
    void mistakesCounterIncrementTest() {
        var session = new HangmanSession(specificWordWordlist, 5);
        final int oldMistakesCount = session.getCurMistakes();
        session.tryGuess('a');
        final int newMistakesCount = session.getCurMistakes();
        assertThat(oldMistakesCount).isZero();
        assertThat(newMistakesCount).isOne();
    }

    @Test
    @DisplayName("Сохранение числа ошибок, если буква есть в слове")
    void mistakesCounterRightLetterTest() {
        var session = new HangmanSession(specificWordWordlist, 5);
        final int oldMistakesCount = session.getCurMistakes();
        session.tryGuess('s');
        final int newMistakesCount = session.getCurMistakes();
        assertThat(oldMistakesCount).isZero();
        assertThat(newMistakesCount).isZero();
    }

    @Test
    @DisplayName("Проверка корректности значений GuessResult")
    void guessResultCorrectnessTest() {
        var sessionWithOneMistake = new HangmanSession(specificWordWordlist, 1);
        final GuessResult defeatResult = sessionWithOneMistake.tryGuess('a');

        var winSession = new HangmanSession(specificWordWordlist, 5);
        final GuessResult failedGuessResult = winSession.tryGuess('a');
        final GuessResult successfulGuessResult1 = winSession.tryGuess('s');
        final GuessResult successfulGuessResult2 = winSession.tryGuess('p');
        final GuessResult successfulGuessResult3 = winSession.tryGuess('e');
        final GuessResult successfulGuessResult4 = winSession.tryGuess('c');
        final GuessResult successfulGuessResult5 = winSession.tryGuess('i');
        final GuessResult winGuessResult = winSession.tryGuess('f');

        assertThat(defeatResult).isInstanceOf(GuessResult.Defeat.class);
        assertThat(failedGuessResult).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(successfulGuessResult1).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult2).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult3).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult4).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult5).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(winGuessResult).isInstanceOf(GuessResult.Win.class);
    }

    @Test
    @DisplayName("Проверка корректности состояния")
    void stateCorrectnessTest() {
        var session = new HangmanSession(specificWordWordlist, 5);
        final int arrayLength = "specific".length();
        final String clearState = session.getState();
        session.tryGuess('a');
        final String failedGuessState = session.getState();
        session.tryGuess('s');
        final String successfulGuessState1 = session.getState();
        session.tryGuess('p');
        final String successfulGuessState2 = session.getState();
        session.tryGuess('e');
        final String successfulGuessState3 = session.getState();
        session.tryGuess('c');
        final String successfulGuessState4 = session.getState();
        session.tryGuess('i');
        final String successfulGuessState5 = session.getState();
        session.tryGuess('f');
        final String winState = session.getState();

        assertThat(clearState).hasSize(arrayLength).isEqualTo("********");
        assertThat(failedGuessState).hasSize(arrayLength).isEqualTo("********");
        assertThat(successfulGuessState1).hasSize(arrayLength).isEqualTo("s*******");
        assertThat(successfulGuessState2).hasSize(arrayLength).isEqualTo("sp******");
        assertThat(successfulGuessState3).hasSize(arrayLength).isEqualTo("spe*****");
        assertThat(successfulGuessState4).hasSize(arrayLength).isEqualTo("spec***c");
        assertThat(successfulGuessState5).hasSize(arrayLength).isEqualTo("speci*ic");
        assertThat(winState).hasSize(arrayLength).isEqualTo("specific");
    }
}
