package edu.project2;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:RegexpSinglelineJava"})
    public static void main(String[] args) {
        final int width = 10;
        final int height = 10;

        Generator generator = new RecursiveBacktrackingGenerator(width, height);
        Maze maze = generator.generate();

        System.out.print(Renderer.render(maze));
    }
}
