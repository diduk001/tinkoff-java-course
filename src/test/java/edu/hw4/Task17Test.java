package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.BARBOS;
import static edu.hw4.SampleAnimals.BELKA;
import static edu.hw4.SampleAnimals.DORY;
import static edu.hw4.SampleAnimals.MARY_JANE;
import static edu.hw4.SampleAnimals.MURKA;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static edu.hw4.SampleAnimals.SPIDEY;
import static edu.hw4.SampleAnimals.TWEETIE;
import static org.assertj.core.api.Assertions.assertThat;

public class Task17Test {
    @Test
    @DisplayName("Тест задания 17 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Boolean result = Main.spidersBitesMoreThanDogs(sampleList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Тест с результатом true")
    void trueTest() {
        final List<Animal> angrySpidersList = List.of(BARBOS, BELKA, SPIDEY, MARY_JANE, MARY_JANE);
        final Boolean result = Main.spidersBitesMoreThanDogs(angrySpidersList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Тест с результатом false")
    void falseTest() {
        final List<Animal> angryDogsList = List.of(BARBOS, BARBOS, BELKA, SPIDEY, MARY_JANE);
        final Boolean result = Main.spidersBitesMoreThanDogs(angryDogsList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Одинаковый процент кусающихся собак и пауков")
    void equalFrequencyTest() {
        final List<Animal> equalFrequencyList = List.of(BARBOS, MARY_JANE, MARY_JANE);
        final Boolean result = Main.spidersBitesMoreThanDogs(equalFrequencyList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Тест без собак")
    void noDogsTest() {
        final List<Animal> noDogsList = List.of(SPIDEY, MARY_JANE);
        final Boolean result = Main.spidersBitesMoreThanDogs(noDogsList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Тест без пауков")
    void noSpidersTest() {
        final List<Animal> noSpidersList = List.of(BELKA, BARBOS);
        final Boolean result = Main.spidersBitesMoreThanDogs(noSpidersList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Тест без собак и пауков")
    void noDogsAndSpidersTest() {
        final List<Animal> noDogsAndSpidersList = List.of(MURKA, DORY, TWEETIE);
        final Boolean result = Main.spidersBitesMoreThanDogs(noDogsAndSpidersList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Boolean result = Main.spidersBitesMoreThanDogs(emptyList);
        assertThat(result).isFalse();
    }
}
