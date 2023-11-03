package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.BARBOS;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Test
    @DisplayName("Тест задания 7 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Animal result = Main.kthOldestAnimal(sampleList, 3);
        assertThat(result).isEqualTo(BARBOS);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> Main.kthOldestAnimal(emptyList, 1));
    }

    @Test
    @DisplayName("Отрицательное значение K")
    void negativeKTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        assertThrows(IllegalArgumentException.class, () -> Main.kthOldestAnimal(sampleList, -10));
    }
}
