package edu.project3;

import java.io.File;
import java.util.List;

public final class Main {
    private Main() {
        throw new IllegalStateException("This is a utility class.");
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        PixelGrid image = new AtomicPixelGrid(1920, 1080, Colors.BLACK);
        image = new MultiThreadedRenderer(25).render(
            image,
            List.of(Colors.RED, Colors.CYAN, Colors.PURPLE, Colors.DIMMED_BLUE, Colors.BLUE),
            Math::sin,
            1_000_000,
            (short) 10
        );

        ImageUtils.save(image, ImageUtils.Format.PNG, new File("image.png"));
    }
}
