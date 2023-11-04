package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("Тест задания 5 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        sampleList.remove(SampleAnimals.MURKA); // Теперь самцов больше чем самок
        final Animal.Sex result = Main.getMaxSex(sampleList);
        final Animal.Sex expected = Animal.Sex.M;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        assertThrows(IllegalArgumentException.class, () -> Main.getMaxSex(emptyList));
    }
}
