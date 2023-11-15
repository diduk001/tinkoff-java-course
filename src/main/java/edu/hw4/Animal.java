package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public record Animal(
    Type type,
    String name,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    private static final int CAT_AND_DOG_PAWS = 4;
    private static final int BIRD_PAWS = 2;
    private static final int FISH_PAWS = 0;
    private static final int SPIDER_PAWS = 8;

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CAT_AND_DOG_PAWS;
            case BIRD -> BIRD_PAWS;
            case FISH -> FISH_PAWS;
            case SPIDER -> SPIDER_PAWS;
        };
    }

    public Set<ValidationError> validate() {
        HashSet<ValidationError> errors = new HashSet<>();

        if (type() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.TYPE_IS_NULL));
        }
        if (name() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.NAME_IS_NULL));
        } else if (name().isBlank()) {
            errors.add(new ValidationError(ValidationError.ErrorType.NAME_IS_EMPTY));
        }
        if (sex() == null) {
            errors.add(new ValidationError(ValidationError.ErrorType.SEX_IS_NULL));
        }
        if (age() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.AGE_IS_NEGATIVE));
        }
        if (height() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.HEIGHT_IS_NEGATIVE));
        }
        if (weight() < 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.WEIGHT_IS_NEGATIVE));
        }

        if (errors.isEmpty()) {
            errors.add(new ValidationError(ValidationError.ErrorType.VALIDATED_CORRECTLY));
        }
        return errors;
    }

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }
}
