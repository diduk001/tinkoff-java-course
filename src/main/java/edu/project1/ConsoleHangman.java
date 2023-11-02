package edu.project1;

import java.util.Scanner;

@SuppressWarnings("checkstyle:RegexpSinglelineJava") final class ConsoleHangman {
    private static final char GIVE_UP_CHAR = '-'; // Символ, который обозначает то, что игрок сдался
    private static final int MAX_MISTAKES = 5; // Максимальное кол-во ошибок, которые может допустить игрок
    private final HangmanSession session; // Сессия игры
    private final Scanner inputScanner; // Сканер для того, чтобы считывать символы с клавиатуры

    ConsoleHangman() {
        // Создаём словарь, игровую сессию, и инициализируем сканер
        Wordlist wordlist = new SimpleWordlist();
        session = new HangmanSession(wordlist, MAX_MISTAKES);
        inputScanner = new Scanner(System.in);
    }

    public void run() {
        printWelcomeMessage(); // В начале игры печатается приветственное сообщение

        while (session.isPlayable()) { // Пока можем играть - играем
            printSessionState(); // Печатаем состояние игры
            final char guess = inputLetter(); // Пользователь вводит букву
            if (guess == GIVE_UP_CHAR) { // Если игрок сдаётся
                System.out.println("You gave up :^(");
                printGuessResult(session.getDefeat());
                return;
            }

            var guessResult = session.tryGuess(guess); // Пробуем угадать букву и выводим результат
            printGuessResult(guessResult);
        }
    }

    private char inputLetter() {
        while (true) {
            System.out.println("Pick a letter: ");
            System.out.print("> ");
            final String userInput = inputScanner.nextLine(); // Пользователь вводит строку

            if (userInput.length() != 1) { // Длина строки должна быть 1
                System.out.println("Input only one character");
                continue;
            }

            final char userInputChar = userInput.charAt(0); // Единственный символ строки
            if (Character.isLetter(userInputChar)) {
                // Если символ является буквой, то преобразуем в нижний регистр и возвращаем
                return Character.toLowerCase(userInputChar);
            } else if (userInputChar == GIVE_UP_CHAR) {
                // Если пользователь решил сдавать, то возвращаем '-'
                return GIVE_UP_CHAR;
            } else {
                // Если символ не является ни буквой, ни '-', то выводим памятку и начинаем цикл сначала
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
