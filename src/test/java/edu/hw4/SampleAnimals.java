package edu.hw4;

import java.util.List;

public class SampleAnimals {
    public static final Animal MURZIK =
        new Animal(Animal.Type.CAT, "Murzik", Animal.Sex.M, 7, 45, 17, false);
    public static final Animal MURKA =
        new Animal(Animal.Type.CAT, "Murka", Animal.Sex.F, 9, 48, 13, true);
    public static final Animal BARBOS =
        new Animal(Animal.Type.DOG, "Barbos", Animal.Sex.M, 12, 53, 18, true);
    public static final Animal BELKA =
        new Animal(Animal.Type.DOG, "Belka", Animal.Sex.F, 10, 49, 21, false);
    public static final Animal TWEETIE =
        new Animal(Animal.Type.BIRD, "Tweetie", Animal.Sex.M, 5, 12, 6, false);
    public static final Animal KLEO =
        new Animal(Animal.Type.BIRD, "Kleo", Animal.Sex.F, 8, 37, 20, true);
    public static final Animal NEMO =
        new Animal(Animal.Type.FISH, "Nemo", Animal.Sex.M, 4, 11, 5, false);
    public static final Animal DORY =
        new Animal(Animal.Type.FISH, "Dory", Animal.Sex.F, 3, 7, 9, true);
    public static final Animal SPIDEY =
        new Animal(Animal.Type.SPIDER, "Spidey", Animal.Sex.M, 13, 10, 7, false);
    public static final Animal MARY_JANE =
        new Animal(Animal.Type.SPIDER, "Mary Jane", Animal.Sex.F, 14, 8, 5, true);

    public static final List<Animal> SAMPLE_ANIMAL_LISTS = List.of(
        MURZIK, MURKA, BARBOS, BELKA, TWEETIE, KLEO, NEMO, DORY, SPIDEY, MARY_JANE
    );
}
