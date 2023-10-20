package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project1Test {
    final Project1.Wordlist specificWordWordlist = () -> "specific";

    @Test
    @DisplayName("Неположительное максимальное кол-во ошибок")
    void notPositiveMaxMistakes() {
        boolean exceptionOnZero = false;
        boolean exceptionOnNegative = false;

        try {
            new Project1.Session(new Project1.SimpleWordlist(), 0);
        } catch (Exception e) {
            exceptionOnZero = true;
        }

        try {
            new Project1.Session(new Project1.SimpleWordlist(), -100);
        } catch (Exception e) {
            exceptionOnNegative = true;
        }

        assertThat(exceptionOnZero).isTrue();
        assertThat(exceptionOnNegative).isTrue();
    }

    @Test
    @DisplayName("Словарь с пустой строкой")
    void emptyAnswerSession() {
        boolean exceptionOnEmpty = false;
        var emptyWordWordlist = new Project1.Wordlist() {
            public String getRandomWord() {
                return "";
            }
        };

        try {
            new Project1.Session(emptyWordWordlist, 5);
        } catch (Exception e) {
            exceptionOnEmpty = true;
        }

        assertThat(exceptionOnEmpty).isTrue();
    }

    @Test
    @DisplayName("Увеличение числа ошибок при ошибке")
    void mistakesCounterIncrementTest() {
        var session = new Project1.Session(specificWordWordlist, 5);
        final int oldMistakesCount = session.getCurMistakes();
        session.tryGuess('a');
        final int newMistakesCount = session.getCurMistakes();
        assertThat(oldMistakesCount).isZero();
        assertThat(newMistakesCount).isOne();
    }

    @Test
    @DisplayName("Сохранение числа ошибок при правильной букве")
    void mistakesCounterRightLetterTest() {
        var session = new Project1.Session(specificWordWordlist, 5);
        final int oldMistakesCount = session.getCurMistakes();
        session.tryGuess('s');
        final int newMistakesCount = session.getCurMistakes();
        assertThat(oldMistakesCount).isZero();
        assertThat(newMistakesCount).isZero();
    }

    @Test
    @DisplayName("Проверка правильности значений GuessResult")
    void guessResultCorrectnessTest() {
        var sessionWithOneMistake = new Project1.Session(specificWordWordlist, 1);
        final Project1.GuessResult defeatResult = sessionWithOneMistake.tryGuess('a');

        var winSession = new Project1.Session(specificWordWordlist, 5);
        final Project1.GuessResult failedGuessResult = winSession.tryGuess('a');
        final Project1.GuessResult successfulGuessResult1 = winSession.tryGuess('s');
        final Project1.GuessResult successfulGuessResult2 = winSession.tryGuess('p');
        final Project1.GuessResult successfulGuessResult3 = winSession.tryGuess('e');
        final Project1.GuessResult successfulGuessResult4 = winSession.tryGuess('c');
        final Project1.GuessResult successfulGuessResult5 = winSession.tryGuess('i');
        final Project1.GuessResult winGuessResult = winSession.tryGuess('f');

        assertThat(defeatResult).isInstanceOf(Project1.GuessResult.Defeat.class);
        assertThat(failedGuessResult).isInstanceOf(Project1.GuessResult.FailedGuess.class);
        assertThat(successfulGuessResult1).isInstanceOf(Project1.GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult2).isInstanceOf(Project1.GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult3).isInstanceOf(Project1.GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult4).isInstanceOf(Project1.GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuessResult5).isInstanceOf(Project1.GuessResult.SuccessfulGuess.class);
        assertThat(winGuessResult).isInstanceOf(Project1.GuessResult.Win.class);
    }

    @Test
    @DisplayName("Проверка корректности состояния")
    void stateCorrectnessTest() {
        var session = new Project1.Session(specificWordWordlist, 5);
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
