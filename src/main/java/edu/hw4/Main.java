package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class Main {
    private static final int TASK11_HEIGHT = 100;

    public List<Animal> sortAnimalByHeight(List<Animal> animals) {
        animals.sort(Comparator.comparingInt(Animal::height));
        return animals;
    }

    public List<Animal> chooseKHeaviest(List<Animal> animals, int k) {
        animals.sort(Comparator.comparingInt(Animal::weight).reversed());
        return animals.subList(0, k);
    }

    public Map<Animal.Type, Integer> countAnimalByTypes(List<Animal> animals) {
        HashMap<Animal.Type, Integer> typeCounter = new HashMap<>();
        for (Animal animal : animals) {
            Integer curCount = typeCounter.getOrDefault(animal.type(), 0);
            typeCounter.replace(animal.type(), curCount + 1);
        }
        return typeCounter;
    }

    public Animal getLongestNameAnimal(List<Animal> animals) {
        return Collections.max(
            animals,
            Comparator.comparingInt(animal -> animal.name().length())
        );
    }

    public Animal.Sex getMaxSex(List<Animal> animals) {
        int maleCount = 0;
        int femaleCount = 0;

        for (Animal animal : animals) {
            if (animal.sex() == Animal.Sex.F) {
                femaleCount++;
            } else {
                maleCount++;
            }
        }

        return (maleCount > femaleCount) ? Animal.Sex.M : Animal.Sex.F;
    }

    public Map<Animal.Type, Animal> heaviestAnimalOfEachType(List<Animal> animals) {
        Map<Animal.Type, Animal> result = new HashMap<>();

        for (Animal animal : animals) {
            Animal.Type key = animal.type();
            if (!result.containsKey(key)) {
                result.put(key, animal);
                continue;
            }

            Animal oldValue = result.get(key);
            if (oldValue.weight() < animal.weight()) {
                result.put(key, animal);
            }
        }

        return result;
    }

    public Animal kthOldestAnimal(List<Animal> animals, int k) {
        animals.sort(Comparator.comparingInt(Animal::age).reversed());
        return animals.get(k - 1);
    }

    public Optional<Animal> heaviestAnimalSmallerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public Integer pawsSum(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public List<Animal> animalsWithAgeIsNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public List<Animal> bigAnimalsThatCanBite(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > TASK11_HEIGHT)
            .toList();
    }

    public Integer countAnimalsWithWeightMoreThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    private Integer countWordsInString(String str) {
        return str.split("\\s+").length;
    }

    public List<Animal> animalsWithNameOfMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> countWordsInString(animal.name()) > 2)
            .toList();
    }

    public Boolean isDogTallerThanKInList(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(
                animal ->
                    animal.type() == Animal.Type.DOG
                        && animal.height() > k
            );
    }

    public Integer summaryWeightOfAnimalsFromKToLYearsOld(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> k <= animal.age())
            .filter(animal -> animal.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    public List<Animal> sortByTypeThanBySexThanByName(List<Animal> animals) {
        animals.sort(
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
        );

        return animals;
    }

    public Boolean spiderBitesMoreThanDogs(List<Animal> animals) {
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

    public Optional<Animal> heaviestFishFromManyLists(List<Animal>... animalsLists) {
        return Arrays.stream(animalsLists)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::name, Animal::validate));
    }

    public Map<String, String> prettyValidateAnimals(List<Animal> animals) {
        Map<String, Set<ValidationError>> validated = validateAnimals(animals);
        Map<String, String> result = new HashMap<>();
        validated.forEach(
            (name, errorSet) ->
                result.put(
                    name,
                    errorSet.stream()
                        .map(ValidationError::toString)
                        .collect(Collectors.joining(" "))
                ));

        return result;
    }

}
