package edu.project2;

public final class Renderer {
    private Renderer() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static String render(Maze maze) {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            StringBuilder line = new StringBuilder();

            for (int col = 0; col < maze.getWidth(); col++) {
                char curChar = maze.isWall(row, col) ? '#' : ' ';
                line.append(curChar);
            }

            if (row != maze.getHeight() - 1) {
                line.append('\n');
            }

            result.append(line);
        }
        return result.toString();
    }
}
