package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class MyFixedThreadPool implements ThreadPool {
    int threadsNum;
    BlockingQueue<Runnable> taskQueue;
    Thread[] threads;
    boolean isWorking;

    private MyFixedThreadPool() {
    }

    public static MyFixedThreadPool create(int threadsNum) {
        MyFixedThreadPool result = new MyFixedThreadPool();

        result.threadsNum = threadsNum;
        result.taskQueue = new LinkedBlockingQueue<>();
        result.threads = new Thread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            result.threads[i] = new Thread(() -> {
                while (result.isWorking) {
                    try {
                        Runnable task = result.taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        result.isWorking = true;

        return result;
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (isWorking) {
            taskQueue.add(task);
        }
    }

    @Override
    public void close() throws Exception {
        isWorking = false;
    }
}
