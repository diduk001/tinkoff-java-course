package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Main {
    private static final int TASK11_HEIGHT = 100;

    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static List<Animal> sortAnimalByHeight(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> chooseKHeaviest(List<Animal> animals, int k) throws IllegalArgumentException {
        if (k < 0) {
            throw new IllegalArgumentException("K can't be negative");
        }
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> countAnimalByTypes(List<Animal> animals) {
        return animals
            .stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.counting()
                ));
    }

    public static Animal getLongestNameAnimal(List<Animal> animals) {
        if (animals.isEmpty()) {
            throw new IllegalArgumentException("Animals list can't be empty");
        }
        return animals
            .stream()
            .max(Comparator.comparingInt(
                animal -> animal.name().length()))
            .get();
    }

    public static Animal.Sex getMaxSex(List<Animal> animals) {
        if (animals.isEmpty()) {
            throw new IllegalArgumentException("Animals list is an empty sequence");
        }
        long maleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long femaleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();
        return (maleCount > femaleCount) ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> heaviestAnimalOfEachType(List<Animal> animals) {
        return animals
            .stream()
            .collect(
                Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
                )
            );
    }

    public static Animal kthOldestAnimal(List<Animal> animals, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        } else if (animals.size() < k) {
            throw new IllegalArgumentException("K must be less or equal to animals.size()");
        }
        animals.sort(Comparator.comparingInt(Animal::age).reversed());
        return animals.get(k - 1);
    }

    public static Optional<Animal> heaviestAnimalSmallerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer pawsSum(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> animalsWithAgeIsNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public static List<Animal> bigAnimalsThatCanBite(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > TASK11_HEIGHT)
            .toList();
    }

    public static Integer countAnimalsWithWeightMoreThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    private static Integer countWordsInString(String str) {
        return str.split("\\s+").length;
    }

    public static List<Animal> animalsWithNameOfMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> countWordsInString(animal.name()) > 2)
            .toList();
    }

    public static Boolean isDogTallerThanKInList(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(
                animal ->
                    animal.type() == Animal.Type.DOG
                        && animal.height() > k
            );
    }

    public static Integer summaryWeightOfAnimalsFromKToLYearsOld(List<Animal> animals, int k, int l) {
        if (k > l) {
            throw new IllegalArgumentException("K must be less or equal to L");
        }
        return animals.stream()
            .filter(animal -> k <= animal.age())
            .filter(animal -> animal.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    public static List<Animal> sortByTypeThanBySexThanByName(List<Animal> animals) {
        animals.sort(
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
        );

        return animals;
    }

    public static Boolean spidersBitesMoreThanDogs(List<Animal> animals) {
        int spidersCount = (int) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .count();

        int dogCount = (int) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .count();

        if (spidersCount == 0 || dogCount == 0) {
            return false;
        }

        int bitingSpidersCount = (int) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .count();

        int bitingDogsCount = (int) animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .filter(Animal::bites)
            .count();

        float spiderBiteFrequency = (float) bitingSpidersCount / spidersCount;
        float dogBiteFrequency = (float) bitingDogsCount / dogCount;

        return spiderBiteFrequency > dogBiteFrequency;
    }

    @SafeVarargs
    public static Optional<Animal> heaviestFishFromManyLists(List<Animal>... animalsLists) {
        return Arrays.stream(animalsLists)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, Animal::validate));
    }

    public static Map<String, String> prettyValidateAnimals(List<Animal> animals) {
        Map<String, Set<ValidationError>> validated = validateAnimals(animals);
        Map<String, String> result = new HashMap<>();
        validated.forEach(
            (name, errorSet) ->
                result.put(
                    name,
                    errorSet.stream()
                        .map(ValidationError::toString)
                        .collect(Collectors.joining(", "))
                ));

        return result;
    }

}
