package edu.project3;

public final class Colors {
    public static final MyColor BLACK = new MyColor(0, 0, 0);
    public static final MyColor WHITE = new MyColor(255, 255, 255);
    public static final MyColor RED = new MyColor(255, 0, 0);
    public static final MyColor GREEN = new MyColor(0, 255, 0);
    public static final MyColor BLUE = new MyColor(0, 0, 255);
    public static final MyColor CYAN = new MyColor(255, 0, 255);
    public static final MyColor PURPLE = new MyColor(127, 0, 127);
    public static final MyColor DIMMED_BLUE = new MyColor(0, 0, 127);

    private Colors() {
        throw new IllegalStateException("This is a utility class.");
    }
}
