package edu.project3;

public interface Pixel {
    MyColor getColor();

    void mixWith(MyColor other);

    void hit();

    double getNormal();

    void normalize(double gamma);
}
