package edu.project3;

public final class Colors {
    public static final NonAtomicColor BLACK = new NonAtomicColor(0, 0, 0);
    public static final NonAtomicColor WHITE = new NonAtomicColor(255, 255, 255);
    public static final NonAtomicColor RED = new NonAtomicColor(255, 0, 0);
    public static final NonAtomicColor GREEN = new NonAtomicColor(0, 255, 0);
    public static final NonAtomicColor BLUE = new NonAtomicColor(0, 0, 255);
    public static final NonAtomicColor CYAN = new NonAtomicColor(255, 0, 255);
    public static final NonAtomicColor PURPLE = new NonAtomicColor(127, 0, 127);
    public static final NonAtomicColor DIMMED_BLUE = new NonAtomicColor(0, 0, 127);

    private Colors() {
        throw new IllegalStateException("This is a utility class.");
    }
}
