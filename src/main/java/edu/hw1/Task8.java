package edu.hw1;

public class Task8 {
    private static boolean isValidCoordinates(final int row, final int col) {
        return 0 <= row && row <= 7 && 0 <= col && col <= 7;
    }

    public static boolean knightBoardCapture(final int[][] board) {
        final int[] rowDelta = {-1, -1, 1, 1, -2, -2, 2, 2};
        final int[] colDelta = {-2, 2, -2, 2, -1, 1, -1, 1};

        for (int rowIdx = 0; rowIdx < 8; rowIdx++) {
            for (int colIdx = 0; colIdx < 8; colIdx++) {
                if (board[rowIdx][colIdx] == 0) {
                    continue;
                }

                for (int deltaIdx = 0; deltaIdx < 8; deltaIdx++) {
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
