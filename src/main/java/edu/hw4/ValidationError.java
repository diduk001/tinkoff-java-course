package edu.hw4;

public record ValidationError(ErrorType type) {
    public String toString() {
        return switch (type) {
            case TYPE_IS_NULL -> "Animal's type is null";
            case NAME_IS_NULL -> "Animal's name is null";
            case NAME_IS_EMPTY -> "Animal's name is empty";
            case SEX_IS_NULL -> "Animal's sex is null";
            case AGE_IS_NEGATIVE -> "Animal's age is negative";
            case HEIGHT_IS_NEGATIVE -> "Animal's height is negative";
            case WEIGHT_IS_NEGATIVE -> "Animal's weight is negative";
            case VALIDATED_CORRECTLY -> "Validated correctly";
        };
    }

    public enum ErrorType {
        TYPE_IS_NULL,
        NAME_IS_NULL,
        NAME_IS_EMPTY,
        SEX_IS_NULL,
        AGE_IS_NEGATIVE,
        HEIGHT_IS_NEGATIVE,
        WEIGHT_IS_NEGATIVE,
        VALIDATED_CORRECTLY
    }
}
