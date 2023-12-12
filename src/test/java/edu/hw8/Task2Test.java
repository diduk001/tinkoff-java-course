package edu.hw8;

import edu.hw8.Task2.MyFixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @ParameterizedTest(name = "Тест с {0} потоками")
    @ValueSource(ints = {1, 3, 5, 7, 10})
    void nThreadsTest(int nThreads) {
        final ConcurrentMap<Integer, Integer> fibByIndex = new ConcurrentHashMap<>();
        final ThreadPool pool = MyFixedThreadPool.create(nThreads);

        for (int idx : new int[] {1, 10, 20, 30, 40}) {
            pool.execute(() -> {
                int a = 0;
                int b = 1;
                for (int i = 0; i < idx; i++) {
                    int c = b;
                    b = a + b;
                    a = c;
                }
                fibByIndex.put(idx, a);
            });
        }
        pool.start();

        // Wait for all threads to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }

        final Map<Integer, Integer> expected = Map.of(
            1, 1,
            10, 55,
            20, 6765,
            30, 832040,
            40, 102334155
        );

        assertThat(fibByIndex)
            .hasSize(5)
            .containsAllEntriesOf(expected);
    }
}
