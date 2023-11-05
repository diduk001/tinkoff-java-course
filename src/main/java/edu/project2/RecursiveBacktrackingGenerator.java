package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RecursiveBacktrackingGenerator implements Generator {
    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, -1, 0, 1};
    private final int width;
    private final int height;
    private final boolean[][] gridWalls;

    public RecursiveBacktrackingGenerator(int width, int height) {
        this.width = width;
        this.height = height;

        gridWalls = new boolean[2 * height + 1][2 * width + 1];

        for (int row = 0; row < 2 * height + 1; row++) {
            for (int col = 0; col < 2 * width + 1; col++) {
                gridWalls[row][col] = true;
            }
        }
    }

    @Override
    public Maze generate() {
        generateRecursively(0, 0);
        return new Maze(gridWalls);
    }

    private void generateRecursively(int curRow, int curCol) {
        freeCell(curRow, curCol);

        List<Direction> directions = new ArrayList<>(List.of(
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT
        ));
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            int newRow = curRow + DELTA_ROW[direction.ordinal()];
            int newCol = curCol + DELTA_COL[direction.ordinal()];

            if (!isValidCell(newRow, newCol) || isFree(newRow, newCol)) {
                continue;
            }

            carveTunnel(curRow, curCol, newRow, newCol);
            generateRecursively(newRow, newCol);
        }
    }

    private boolean isValidCell(int row, int col) {
        return 0 <= row && row < height
            && 0 <= col && col < width;
    }

    private boolean isFree(int row, int col) {
        return !gridWalls[1 + row * 2][1 + col * 2];
    }

    private void carveTunnel(int row1, int col1, int row2, int col2) {
        int wallRow = ((1 + row1 * 2) + (1 + row2 * 2)) / 2;
        int wallCol = ((1 + col1 * 2) + (1 + col2 * 2)) / 2;
        gridWalls[wallRow][wallCol] = false;
    }

    private void freeCell(int row, int col) {
        gridWalls[1 + row * 2][1 + col * 2] = false;
    }

    private enum Direction {
        UP, LEFT, DOWN, RIGHT
    }
}
