package edu.project1;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) { // Создаём и запускаем игру в консоли
        var game = new ConsoleHangman();
        game.run();
    }
}
