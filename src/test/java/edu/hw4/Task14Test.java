package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task14Test {
    @Test
    @DisplayName("Тест задания 14 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Boolean result = Main.isDogTallerThanKInList(sampleList, 300);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final Boolean result = Main.isDogTallerThanKInList(emptyList, 100);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Отрицательное значение K")
    void negativeKTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Boolean result = Main.isDogTallerThanKInList(sampleList, -10);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Большое значение K")
    void largeKTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Boolean result = Main.isDogTallerThanKInList(sampleList, 1000);
        assertThat(result).isFalse();
    }
}
