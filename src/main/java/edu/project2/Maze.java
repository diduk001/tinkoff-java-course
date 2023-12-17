package edu.project2;

import java.util.ArrayList;
import java.util.List;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(final Cell[][] grid) {
        this.height = grid.length;
        if (this.height == 0) {
            throw new IllegalArgumentException("Maze height can't be 0");
        }

        this.width = grid[0].length;
        if (this.width == 0) {
            throw new IllegalArgumentException("Maze width can't be 0");
        }

        this.grid = grid;
    }

    public boolean isValidCoordinates(int row, int col) {
        return 0 <= row && row < this.height
            && 0 <= col && col < this.width;
    }

    public List<CoordinatesPair> connectedCells(int row, int col) {
        List<CoordinatesPair> result = new ArrayList<>();

        // С ячейкой (row, col) соседними являются ячейки (row + DELTA_ROW[i], col + DELTA_COL[i])
        final int[] DELTA_ROW = {-1, 0, 1, 0};
        final int[] DELTA_COL = {0, -1, 0, 1};

        for (int deltaIdx = 0; deltaIdx < DELTA_ROW.length; deltaIdx++) {
            int newRow = row + DELTA_ROW[deltaIdx];
            int newCol = col + DELTA_COL[deltaIdx];
            if (!isValidCoordinates(newRow, newCol) || !canGoFromTo(row, col, newRow, newCol)) {
                continue;
            }

            result.add(new CoordinatesPair(newRow, newCol));
        }
        return result;
    }

    public List<CoordinatesPair> connectedCells(CoordinatesPair coords) {
        return connectedCells(coords.row(), coords.col());
    }

    public boolean canGoFromTo(int row1, int col1, int row2, int col2) {
        if (row1 == row2) {
            return col1 < col2 ? this.grid[row1][col1].isCanGoRight() : this.grid[row1][col1].isCanGoLeft();
        } else {
            return row1 < row2 ? this.grid[row1][col1].isCanGoDown() : this.grid[row1][col1].isCanGoUp();
        }
    }

    public Cell getCell(int row, int col) {
        return this.grid[row][col];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
