package edu.project3;

import java.awt.Color;

public interface MyColor {
    int MIN_VALUE = 0;
    int MAX_VALUE = 255;

    int getR();

    int getG();

    int getB();

    void mixWith(MyColor other);

    void normalize(double gamma, double curNormal);

    Color toAWTColor();

}
