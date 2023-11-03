package edu.hw4;

public record ValidationError(ErrorType type) {
    public String toString() {
        return switch (type) {
            case ANIMAL_IS_NULL -> "Animal is null";
            case TYPE_IS_NULL -> "Animal's Type is null";
            case NAME_IS_NULL -> "Animal's name is null";
            case NAME_IS_EMPTY -> "Animal's name is empty";
            case SEX_IS_NULL -> "Animal's sex is null";
            case AGE_IS_NEGATIVE -> "Animal's age is negative";
            case WEIGHT_IS_NEGATIVE -> "Animal's weight is negative";
            case VALIDATED_CORRECTLY -> "Validated correctly";
        };
    }

    public enum ErrorType {
        ANIMAL_IS_NULL,
        TYPE_IS_NULL,
        NAME_IS_NULL,
        NAME_IS_EMPTY,
        SEX_IS_NULL,
        AGE_IS_NEGATIVE,
        WEIGHT_IS_NEGATIVE,
        VALIDATED_CORRECTLY
    }
}
