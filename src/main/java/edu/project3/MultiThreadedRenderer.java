package edu.project3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadedRenderer implements Renderer {
    private static final double X_BOUND = 1.777;
    private static final double Y_BOUND = 1.0;
    private static final double GAMMA_VALUE = 2.2;
    private final int threads;
    private final ExecutorService threadPool;

    public MultiThreadedRenderer(int threads) {
        this.threads = threads;
        this.threadPool = Executors.newFixedThreadPool(threads);
    }

    @Override
    public PixelGrid render(
        PixelGrid grid,
        List<LinearTransformation> transformations,
        List<MyColor> colors,
        NonLinearTransformation nonLinearTransformation,
        int samples,
        short iterationsPerSample
    ) {
        if (transformations.size() != colors.size()) {
            throw new IllegalArgumentException("Transformations and colors must be same size");
        }

        PixelGrid result = new AtomicPixelGrid(grid.getPixelsArray(), grid.getWidth(), grid.getHeight());
        CountDownLatch tasks = new CountDownLatch(threads);

        for (int threadIdx = 0; threadIdx < threads; threadIdx++) {
            int finalThreadIdx = threadIdx;
            threadPool.execute(() -> {
                for (int sampleNum = finalThreadIdx; sampleNum < samples; sampleNum += threads) {
                    Point samplePoint = Point.generateRandomPoint(-X_BOUND, X_BOUND, -Y_BOUND, Y_BOUND);
                    iterateWithPoint(
                        samplePoint,
                        grid,
                        transformations,
                        colors,
                        nonLinearTransformation,
                        iterationsPerSample
                    );
                    tasks.countDown();
                }
            });
        }

        try {
            tasks.await();
            threadPool.close();
            result.applyGammaCorrection(GAMMA_VALUE);
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Constructor to escape generating random linear transformations
    public PixelGrid render(
        PixelGrid grid,
        List<MyColor> colors,
        NonLinearTransformation nonLinearTransformation,
        int samples,
        short iterationsPerSample
    ) {
        List<LinearTransformation> transformations = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            transformations.add(new LinearTransformation());
        }

        return render(
            grid,
            transformations,
            colors,
            nonLinearTransformation,
            samples,
            iterationsPerSample
        );

    }

    private void iterateWithPoint(
        Point point,
        PixelGrid grid,
        List<LinearTransformation> transformations,
        List<MyColor> colors,
        NonLinearTransformation nonLinearTransformation,
        short steps
    ) {
        Point newPoint = new Point(point.x(), point.y());
        for (short step = 0; step < steps; step++) {
            int linearTransformationIndex = ThreadLocalRandom.current().nextInt(transformations.size());
            LinearTransformation phi = transformations.get(linearTransformationIndex);
            newPoint = newPoint.transform(phi);
            newPoint = new Point(
                nonLinearTransformation.apply(newPoint.x()),
                nonLinearTransformation.apply(newPoint.y())
            );
            int pixelCol = grid.getColFromPoint(newPoint.x(), -X_BOUND, X_BOUND);
            int pixelRow = grid.getRowFromPoint(newPoint.y(), -Y_BOUND, Y_BOUND);

            if (!grid.contains(pixelRow, pixelCol)) {
                continue;
            }
            grid.mixAndHit(pixelRow, pixelCol, colors.get(linearTransformationIndex));
        }
    }
}
