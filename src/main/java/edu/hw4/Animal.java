package edu.hw4;

public record Animal(
    Type type,
    String name,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    static final private int CAT_AND_DOG_PAWS = 4;
    static final private int BIRD_PAWS = 2;
    static final private int FISH_PAWS = 0;
    static final private int SPIDER_PAWS = 8;

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CAT_AND_DOG_PAWS;
            case BIRD -> BIRD_PAWS;
            case FISH -> FISH_PAWS;
            case SPIDER -> SPIDER_PAWS;
        };
    }

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }
}
