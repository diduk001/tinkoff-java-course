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

    // Задача 1.
    // Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<Animal> sortAnimalByHeight(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    // Задача 2.
    // Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
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

    // Задача 3.
    // Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> countAnimalByTypes(List<Animal> animals) {
        return animals
            .stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue
                    )
                ));
    }

    // Задача 4.
    // У какого животного самое длинное имя -> Animal
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

    // Задача 5.
    // Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex getMaxSex(List<Animal> animals) {
        if (animals.isEmpty()) {
            throw new IllegalArgumentException("Animals list is an empty sequence");
        }
        long maleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long femaleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();
        return (maleCount > femaleCount) ? Animal.Sex.M : Animal.Sex.F;
    }

    // Задача 6.
    // Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal
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

    // Задача 7.
    // K-е самое старое животное -> Anima
    public static Animal kthOldestAnimal(List<Animal> animals, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("K must be less or equal to animals.size()"));
    }

    // Задача 8.
    // Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Optional<Animal> heaviestAnimalSmallerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    // Задача 9
    // Сколько в сумме лап у животных в списке -> Integer
    public static Integer pawsSum(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // Задача 10
    // Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<Animal> animalsWithAgeIsNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    // Задача 11
    // Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    public static List<Animal> bigAnimalsThatCanBite(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > TASK11_HEIGHT)
            .toList();
    }

    // Задача 12
    // Сколько в списке животных, вес которых превышает рост -> Integer
    public static Integer countAnimalsWithWeightMoreThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    private static Integer countWordsInString(String str) {
        return str.split("\\s+").length;
    }

    // Задача 13
    // Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<Animal> animalsWithNameOfMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> countWordsInString(animal.name()) > 2)
            .toList();
    }

    // Задача 14.
    // Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean isDogTallerThanKInList(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(
                animal ->
                    animal.type() == Animal.Type.DOG
                        && animal.height() > k
            );
    }

    // Задача 15
    // Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public static Integer summaryWeightOfAnimalsFromKToLYearsOld(List<Animal> animals, int k, int l) {
        if (k > l) {
            throw new IllegalArgumentException("K must be less or equal to L");
        }
        return animals.stream()
            .filter(animal -> k <= animal.age())
            .filter(animal -> animal.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    // Задача 16
    // Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<Animal> sortByTypeThanBySexThanByName(List<Animal> animals) {
        animals.sort(
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
        );

        return animals;
    }

    // Задача 17
    // Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
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

    // Задача 18.
    // Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    @SafeVarargs
    public static Optional<Animal> heaviestFishFromManyLists(List<Animal>... animalsLists) {
        return Arrays.stream(animalsLists)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight));
    }

    // Задача 19.
    // Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>
    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, Animal::validate));
    }

    // Задача 20.
    // Сделать результат предыдущего задания более читабельным: вернуть имя и названия полей с ошибками,
    // объединенные в строку -> Map<String, String>
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
