package edu.hw1;

public class Task8 {
    private static boolean isValidCoordinates(final int row, final int col) {
        return 0 <= row && row <= 7 && 0 <= col && col <= 7;
    }

    public static boolean knightBoardCapture(final int[][] board) {
        final int[] delta_row = {-1, -1, 1, 1, -2, -2, 2, 2};
        final int[] delta_col = {-2, 2, -2, 2, -1, 1, -1, 1};

        for (int cur_row = 0; cur_row < 8; cur_row++) {
            for (int cur_col = 0; cur_col < 8; cur_col++) {
                if (board[cur_row][cur_col] == 0) {
                    continue;
                }

                for (int delta_idx = 0; delta_idx < 8; delta_idx++) {
                    final int new_row = cur_row + delta_row[delta_idx];
                    final int new_col = cur_col + delta_col[delta_idx];
                    if (isValidCoordinates(new_row, new_col) && board[new_row][new_col] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
