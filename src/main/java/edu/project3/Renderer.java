package edu.project3;

import java.util.List;

public interface Renderer {
    PixelGrid render(
        PixelGrid grid,
        List<LinearTransformation> transformations,
        List<MyColor> colors,
        NonLinearTransformation nonLinearTransformation,
        int samples,
        short iterationsPerSample
    );
}
