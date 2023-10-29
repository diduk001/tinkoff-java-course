package edu.project1;

import java.util.Random;

// Простой словарь с 7 словами
final class SimpleWordlist implements Wordlist {
    final private static String[] WORDS = {"person", "stream", "mighty", "prince", "snakes", "zombie", "circus"};

    // Вернуть случайное слово из списка
    @Override
    public String getRandomWord() {
        int randomIdx = new Random().nextInt(WORDS.length);
        return WORDS[randomIdx];
    }
}
