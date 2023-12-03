package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class MultiThreadedCracker implements PasswordCracker {
    int threadsNumber;
    int minLength;
    int maxLength;
    ExecutorService threadPool;

    public MultiThreadedCracker(int minLength, int maxLength, int threadsNumber) {
        if (minLength <= 0 || maxLength <= 0) {
            throw new IllegalArgumentException("minLength and maxLength must be positive values");
        } else if (minLength > maxLength) {
            throw new IllegalArgumentException("minLength must be not greater than maxLength");
        } else if (threadsNumber <= 0) {
            throw new IllegalArgumentException("Threads number must be positive value");
        }

        this.minLength = minLength;
        this.maxLength = maxLength;
        this.threadsNumber = threadsNumber;
        this.threadPool = Executors.newFixedThreadPool(threadsNumber);
    }

    private static String nextByAlphabet(final String current) {
        if (current.isEmpty()) {
            return current;
        }
        int pivotIdx = current.length() - 1;
        while (pivotIdx >= 0
            && current.charAt(pivotIdx) == ALPHABET.charAt(ALPHABET.length() - 1)) {
            pivotIdx--;
        }
        if (pivotIdx == -1) {
            return String.valueOf(ALPHABET.charAt(0)).repeat(current.length() + 1);
        }
        return getNextStringWithPivot(current, pivotIdx);
    }

    private static String getNextStringWithPivot(final String current, int pivotIdx) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < current.length(); i++) {
            if (i < pivotIdx) {
                result.append(current.charAt(i));
            } else if (i == pivotIdx) {
                char curChar = current.charAt(i);
                int curCharIdx = ALPHABET.indexOf(curChar);
                char nextChar = ALPHABET.charAt(curCharIdx + 1);
                result.append(nextChar);
            } else {
                result.append(ALPHABET.charAt(0));
            }
        }
        return result.toString();
    }

    @Override
    public String crackSingle(String hash) {
        final CountDownLatch unfinishedTasks = new CountDownLatch(threadsNumber);
        final List<String> batches = divideToBatches();
        final AtomicBoolean isFound = new AtomicBoolean(false);
        final AtomicReference<String> answer = new AtomicReference<>(null);

        for (int i = 0; i < threadsNumber; i++) {
            final String from = batches.get(i);
            final String to = batches.get(i + 1);

            threadPool.execute(() -> {
                String curString = from;
                while (!curString.equals(to)) {
                    if (isFound.get()) {
                        break;
                    }
                    String hashed = PasswordCracker.md5Hash(curString);
                    if (hashed.equals(hash)) {
                        isFound.set(true);
                        answer.set(curString);
                        break;
                    }

                    curString = nextByAlphabet(curString);
                }
                unfinishedTasks.countDown();
            });
        }

        try {
            unfinishedTasks.await();
            return answer.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> crackDict(Map<String, String> hashDict) {
        final ConcurrentMap<String, String> concurrentHashDict = new ConcurrentHashMap<>(hashDict);

        final CountDownLatch unfinishedTasks = new CountDownLatch(threadsNumber);
        final List<String> batches = divideToBatches();

        final ConcurrentMap<String, String> result = new ConcurrentHashMap<>();

        for (int i = 0; i < threadsNumber; i++) {
            final String from = batches.get(i);
            final String to = batches.get(i + 1);

            threadPool.execute(() -> {
                String curString = from;
                while (!curString.equals(to)) {
                    String hashed = PasswordCracker.md5Hash(curString);
                    if (concurrentHashDict.containsKey(hashed)) {
                        result.put(concurrentHashDict.get(hashed), curString);
                    }
                    curString = nextByAlphabet(curString);
                }
                unfinishedTasks.countDown();
            });
        }

        try {
            unfinishedTasks.await();
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> divideToBatches() {
        long lastIdx = 0;
        for (int i = minLength; i <= maxLength; i++) {
            lastIdx += (long) Math.pow(ALPHABET.length(), i);
        }
        final long batchSize = lastIdx / 10;

        List<String> result = new ArrayList<>(threadsNumber);

        int prevIdx = 0;
        String curString = String.valueOf(ALPHABET.charAt(0)).repeat(minLength);
        result.add(curString);

        for (int curIdx = 0; curIdx < lastIdx; curIdx++) {
            if (curIdx - prevIdx == batchSize) {
                result.add(curString);
                prevIdx = curIdx;
            }
            curString = nextByAlphabet(curString);
        }

        String lastStr = String.valueOf(ALPHABET.charAt(ALPHABET.length() - 1)).repeat(maxLength);
        if (!result.getLast().equals(lastStr)) {
            result.add(lastStr);
        }

        return result;
    }
}
