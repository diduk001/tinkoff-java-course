package edu.project2;

import java.util.List;

public final class Renderer {
    private static final char FILLING_HORIZONTAL_WALLS_CHAR = '┼';
    private static final char VERTICAL_WALLS_CHAR = '│';
    private static final char FILLING_VERTICAL_WALLS_CHAR = ' ';
    private static final char HORIZONTAL_WALLS_CHAR = '─';
    private static final char FREE_SPACE_CHAR = ' ';
    private static final char PATH_CHAR = '■';

    private Renderer() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static String render(Maze maze, List<CoordinatesPair> path) {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            result.append(buildRow(maze, row, path));
        }

        for (int col = 0; col < maze.getWidth(); col++) {
            result.append(FILLING_HORIZONTAL_WALLS_CHAR);
            final char toAppend =
                maze.getCell(maze.getHeight() - 1, col).isCanGoDown() ? FREE_SPACE_CHAR : HORIZONTAL_WALLS_CHAR;
            result.append(toAppend);
        }
        result.append(FILLING_HORIZONTAL_WALLS_CHAR);
        result.append('\n');

        return result.toString();
    }

    public static String render(Maze maze) {
        return render(maze, List.of());
    }

    private static StringBuilder buildRow(Maze maze, int rowIdx, List<CoordinatesPair> path) {
        StringBuilder rowBuilder = new StringBuilder();

        for (int col = 0; col < maze.getWidth(); col++) {
            rowBuilder.append(FILLING_HORIZONTAL_WALLS_CHAR);
            final char toAppend;
            if (!maze.getCell(rowIdx, col).isCanGoUp()) {
                toAppend = HORIZONTAL_WALLS_CHAR;
            } else if (maze.isValidCoordinates(rowIdx - 1, col)
                && path.contains(new CoordinatesPair(rowIdx - 1, col))
                && path.contains(new CoordinatesPair(rowIdx, col))) {
                toAppend = PATH_CHAR;
            } else {
                toAppend = FREE_SPACE_CHAR;
            }

            rowBuilder.append(toAppend);
        }
        rowBuilder.append(FILLING_HORIZONTAL_WALLS_CHAR);
        rowBuilder.append('\n');

        for (int col = 0; col < maze.getWidth(); col++) {
            final char toAppend;
            if (!maze.getCell(rowIdx, col).isCanGoLeft()) {
                toAppend = VERTICAL_WALLS_CHAR;
            } else if (maze.isValidCoordinates(rowIdx, col - 1)
                && path.contains(new CoordinatesPair(rowIdx, col))
                && path.contains(new CoordinatesPair(rowIdx, col - 1))) {
                toAppend = PATH_CHAR;
            } else {
                toAppend = FREE_SPACE_CHAR;
            }

            rowBuilder.append(toAppend);
            if (path.contains(new CoordinatesPair(rowIdx, col))) {
                rowBuilder.append(PATH_CHAR);
            } else {
                rowBuilder.append(FILLING_VERTICAL_WALLS_CHAR);
            }
        }
        final char toAppend =
            maze.getCell(rowIdx, maze.getWidth() - 1).isCanGoRight() ? FREE_SPACE_CHAR : VERTICAL_WALLS_CHAR;

        rowBuilder.append(toAppend);
        rowBuilder.append('\n');

        return rowBuilder;
    }
}
