package edu.project1;

import java.util.Arrays;

class HangmanSession {
    private final String answer; // Ответ
    private final char[] state; // Состояние - какие буквы отгаданы, а какие - нет. Не отгаданные обозначаются '*'
    private final int maxMistakes; // Максимальное допустимое количество ошибок
    private int curMistakes; // Текущее количество ошибок

    HangmanSession(Wordlist wordlist, int maxMistakes) {
        if (maxMistakes <= 0) {
            throw new IllegalArgumentException("maxMistakes can be only positive");
        }

        this.maxMistakes = maxMistakes;
        this.answer = wordlist.getRandomWord(); // Выбираем случайное слово из словаря

        // Выбранное слово не должно быть пустым
        if (this.answer.isEmpty()) {
            throw new IllegalArgumentException("getRandomWord returned empty string");
        }

        state = new char[this.answer.length()];
        Arrays.fill(state, '*'); // '*' обозначает не отгаданную букву

        this.curMistakes = 0; // Инициализируем текущее кол-во ошибок
    }

    // Пользователь пытается отгадать букву
    GuessResult tryGuess(final char guess) {
        boolean isSuccessful = false;

        for (int i = 0; i < answer.length(); i++) {
            // Перебираем все буквы ответа
            // Если догадки нет в ответе - идём дальше
            if (answer.charAt(i) != guess) {
                continue;
            }

            // Если есть - устанавливаем флаг и изменяем состояние
            isSuccessful = true;
            state[i] = guess;
        }

        if (!isSuccessful) { // Если буквы нет в слове
            curMistakes += 1;
            if (curMistakes == maxMistakes) {
                return getDefeat();
            }
            return getFailedGuess();
        } else if (isWon()) { // Если игра закончилась победой
            return getWin();
        } else { // Если буква в слове есть, но игра не закончилась победой
            return getSuccessfulGuess();
        }
    }

    private boolean isWon() {
        // Если ни один из символов состояния не равен '*' - одержана победа
        for (char c : state) {
            if (c == '*') {
                return false;
            }
        }

        return true;
    }

    public GuessResult getDefeat() {
        return new GuessResult.Defeat(answer);
    }

    public GuessResult getSuccessfulGuess() {
        return new GuessResult.SuccessfulGuess();
    }

    public GuessResult getFailedGuess() {
        return new GuessResult.FailedGuess(curMistakes, maxMistakes);
    }

    public GuessResult getWin() {
        return new GuessResult.Win(answer);
    }

    public boolean isPlayable() {
        return !isWon() && curMistakes < maxMistakes;
    }

    public String getState() {
        return new String(state);
    }

    public int getCurMistakes() {
        return curMistakes;
    }
}
