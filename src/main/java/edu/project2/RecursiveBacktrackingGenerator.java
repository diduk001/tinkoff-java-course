package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RecursiveBacktrackingGenerator implements Generator {
    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, -1, 0, 1};
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final boolean[][] visited;

    public RecursiveBacktrackingGenerator(int width, int height) {
        this.width = width;
        this.height = height;

        grid = new Cell[height][width];
        visited = new boolean[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell();
                visited[row][col] = false;
            }
        }
    }

    @Override
    public Maze generate() {
        generateRecursively(0, 0);
        return new Maze(grid);
    }

    private void generateRecursively(int curRow, int curCol) {
        visited[curRow][curCol] = true;
//        System.out.println(curRow + " " + curCol);
        List<Direction> directions = new ArrayList<>(List.of(
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT
        ));
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            int newRow = curRow + DELTA_ROW[direction.ordinal()];
            int newCol = curCol + DELTA_COL[direction.ordinal()];

            if (!isValidCell(newRow, newCol) || visited[newRow][newCol]) {
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

    private void carveTunnel(int row1, int col1, int row2, int col2) {
        if (row1 == row2) {
            if (col1 < col2) {
                grid[row1][col1].freeRight();
                grid[row2][col2].freeLeft();
            } else {
                grid[row1][col1].freeLeft();
                grid[row2][col2].freeRight();
            }
        } else {
            if (row1 < row2) {
                grid[row1][col1].freeDown();
                grid[row2][col2].freeUp();
            } else {
                grid[row1][col1].freeUp();
                grid[row2][col2].freeDown();
            }
        }
    }

    private enum Direction {
        UP, LEFT, DOWN, RIGHT
    }
}
