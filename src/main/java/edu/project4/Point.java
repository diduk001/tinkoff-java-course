package edu.project4;

public record Point(double x, double y) {
    public static Point generateRandomPoint(double xMin, double xMax, double yMin, double yMax) {
        return new Point(
            xMin + Math.random() * (xMax - xMin),
            yMin + Math.random() * (yMax - yMin)
        );
    }

    Point transform(LinearTransformation phi) {
        return new Point(
            x * phi.a() + y * phi.b() + phi.c(),
            x * phi.d() + y * phi.e() + phi.f()
        );
    }
}
