package edu.project3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
        throw new IllegalStateException("This is a utility class.");
    }

    public static void save(PixelGrid grid, Format format, File file) {
        var image = new BufferedImage(grid.width, grid.height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < grid.height; row++) {
            for (int col = 0; col < grid.width; col++) {
                Pixel curPixel = grid.getPixel(row, col);
                MyColor curColor = curPixel.getColor();
                image.setRGB(col, row, curColor.toAWTColor().getRGB());
            }
        }

        try {
            ImageIO.write(image, format.name().toLowerCase(), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Format {
        JPEG, BMP, PNG
    }
}
