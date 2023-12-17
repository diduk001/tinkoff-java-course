package edu.project2;

import java.util.List;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:RegexpSinglelineJava"})
    public static void main(String[] args) {
        final int width = 10;
        final int height = 10;

        Generator generator = new RecursiveBacktrackingGenerator(width, height);
        Solver solver = new BFSSolver();
        Maze maze = generator.generate();
        List<CoordinatesPair> solution =
            solver.solve(maze, new CoordinatesPair(0, 0), new CoordinatesPair(height - 1, width - 1));
        System.out.print(Renderer.render(maze, solution));
    }
}
