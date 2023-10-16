package edu.hw1;

public final class Task8 {
    private static final int BOARD_SIZE = 8;

    private Task8() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static boolean isValidCoordinates(final int row, final int col) {
        return 0 <= row && row < BOARD_SIZE && 0 <= col && col < BOARD_SIZE;
    }

    public static boolean knightBoardCapture(final int[][] board) {
        final int[] rowDelta = {-1, -1, 1, 1, -2, -2, 2, 2};
        final int[] colDelta = {-2, 2, -2, 2, -1, 1, -1, 1};

        for (int rowIdx = 0; rowIdx < BOARD_SIZE; rowIdx++) {
            for (int colIdx = 0; colIdx < BOARD_SIZE; colIdx++) {
                if (board[rowIdx][colIdx] == 0) {
                    continue;
                }

                for (int deltaIdx = 0; deltaIdx < BOARD_SIZE; deltaIdx++) {
                    final int newRow = rowIdx + rowDelta[deltaIdx];
                    final int newCol = colIdx + colDelta[deltaIdx];
                    if (isValidCoordinates(newRow, newCol) && board[newRow][newCol] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
