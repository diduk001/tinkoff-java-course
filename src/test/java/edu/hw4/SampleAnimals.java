package edu.hw4;

import java.util.List;

public class SampleAnimals {
    public static final Animal MURZIK =
        new Animal(Animal.Type.CAT, "Murzik", Animal.Sex.M, 7, 450, 17, false);
    public static final Animal MURKA =
        new Animal(Animal.Type.CAT, "Murka", Animal.Sex.F, 9, 480, 13, true);
    public static final Animal BARBOS =
        new Animal(Animal.Type.DOG, "Barbos", Animal.Sex.M, 12, 530, 18, true);
    public static final Animal BELKA =
        new Animal(Animal.Type.DOG, "Belka", Animal.Sex.F, 10, 490, 21, false);
    public static final Animal TWEETIE =
        new Animal(Animal.Type.BIRD, "Tweetie", Animal.Sex.M, 5, 120, 6, false);
    public static final Animal KLEO =
        new Animal(Animal.Type.BIRD, "Kleo", Animal.Sex.F, 8, 370, 20, true);
    public static final Animal NEMO =
        new Animal(Animal.Type.FISH, "Nemo", Animal.Sex.M, 4, 110, 5, false);
    public static final Animal DORY =
        new Animal(Animal.Type.FISH, "Dory", Animal.Sex.F, 3, 70, 9, true);
    public static final Animal SPIDEY =
        new Animal(Animal.Type.SPIDER, "Spidey", Animal.Sex.M, 13, 100, 7, false);
    public static final Animal MARY_JANE =
        new Animal(Animal.Type.SPIDER, "Mary Jane", Animal.Sex.F, 14, 80, 5, true);

    public static final List<Animal> SAMPLE_ANIMAL_LIST = List.of(
        MURZIK, MURKA, BARBOS, BELKA, TWEETIE, KLEO, NEMO, DORY, SPIDEY, MARY_JANE
    );
}
