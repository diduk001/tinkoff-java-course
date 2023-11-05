package edu.project2;

import java.util.ArrayList;
import java.util.List;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(final boolean[][] gridWalls) {
        height = gridWalls.length;
        width = gridWalls[0].length;
        this.grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(gridWalls[row][col]);
            }
        }
    }

    public Maze(final Cell[][] grid) {
        height = grid.length;
        if (height == 0) {
            throw new IllegalArgumentException("Maze height can't be 0");
        }

        width = grid[0].length;
        if (width == 0) {
            throw new IllegalArgumentException("Maze width can't be 0");
        }

        this.grid = grid;
    }

    public boolean isValidCoordinates(int row, int col) {
        return 0 <= row && row < height
            && 0 <= col && col < width;
    }

    public boolean isWall(int row, int col) {
        if (isValidCoordinates(row, col)) {
            return grid[row][col].isWall();
        }
        throw new IndexOutOfBoundsException("Cell index out of bounds");
    }

    public boolean isWall(final CoordinatesPair coordinates) {
        return isWall(coordinates.row(), coordinates.col());
    }

    public List<CoordinatesPair> connectedCells(int row, int col) {
        ArrayList<CoordinatesPair> result = new ArrayList<>();
        if (isWall(row, col)) {
            return result;
        }

        // С ячейкой (row, col) соседними являются ячейки (row + DELTA_ROW[i], col + DELTA_COL[i])
        final int[] DELTA_ROW = {-1, 0, 1, 0};
        final int[] DELTA_COL = {0, -1, 0, 1};

        for (int deltaIdx = 0; deltaIdx < DELTA_ROW.length; deltaIdx++) {
            int newRow = row + DELTA_ROW[deltaIdx];
            int newCol = col + DELTA_COL[deltaIdx];
            if (!isValidCoordinates(newRow, newCol) || isWall(newRow, newCol)) {
                continue;
            }

            result.add(new CoordinatesPair(newRow, newCol));
        }
        return result;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
