package edu.hw8.Task3;

import java.util.List;

@SuppressWarnings({"checkstyle:RegexpSinglelineJava", "checkstyle:MagicNumber",
    "checkstyle:MultipleStringLiterals"})
public final class Main {

    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static void main(String[] args) {
        final List<String> hashes = List.of(
            PasswordCracker.md5Hash("1234"),
            PasswordCracker.md5Hash("test"),
            PasswordCracker.md5Hash("pass")
        );

        System.out.println("Single threaded time test:");
        long sumTime = 0;
        SingleThreadCracker cracker = new SingleThreadCracker(3, 5);
        for (String hash : hashes) {
            long timer = System.nanoTime();
            cracker.crackSingle(hash);
            sumTime += (System.nanoTime() - timer);
        }
        System.out.println("Average time: " + sumTime / 3 * 1_000_000_000);

        for (int threads : new int[] {2, 4, 8, 16}) {
            System.out.println("Cracking with " + threads + " threads:");
            sumTime = 0;
            MultiThreadedCracker multiThreadedCracker = new MultiThreadedCracker(3, 5, threads);
            for (String hash : hashes) {
                long timer = System.nanoTime();
                multiThreadedCracker.crackSingle(hash);
                sumTime += (System.nanoTime() - timer);
            }
            System.out.println("Average time: " + sumTime / 3 * 1_000_000_000);
        }
    }
}
