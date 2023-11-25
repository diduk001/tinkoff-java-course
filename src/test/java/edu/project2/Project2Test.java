package edu.project2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project2Test {
    @Test
    @DisplayName("Пустая сетка в конструкторе")
    void emptyGridTest() {
        Cell[][] emptyGrid = new Cell[0][0];
        assertThrows(IllegalArgumentException.class, () -> new Maze(emptyGrid));
    }

    @Test
    @DisplayName("Проверка рендера лабиринта")
    void renderingTest() {
        final Cell[][] grid = {{new Cell(false, false, true, true), new Cell(false, true, true, false)},
            {new Cell(true, false, false, true), new Cell(true, true, false, false)}};
        final String renderGrid = Renderer.render(new Maze(grid));
        final String expected =
            "┼─┼─┼" + System.lineSeparator() +
                "│   │" + System.lineSeparator() +
                "┼ ┼ ┼" + System.lineSeparator() +
                "│   │" + System.lineSeparator() +
                "┼─┼─┼" + System.lineSeparator();

        assertThat(renderGrid).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка рендера лабиринта со стенами")
    void renderingWithWallsTest() {
        final Cell[][] grid = {{new Cell(false, false, true, true), new Cell(false, true, true, false)},
            {new Cell(true, false, false, true), new Cell(true, true, false, false)}};
        final String renderGrid = Renderer.render(new Maze(grid));
        final String expected = "┼─┼─┼" + System.lineSeparator() +
            "│   │" + System.lineSeparator() +
            "┼ ┼ ┼" + System.lineSeparator() +
            "│   │" + System.lineSeparator() +
            "┼─┼─┼" + System.lineSeparator();
        assertThat(renderGrid).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка рендера лабиринта с путём")
    void renderingWithPathTest() {
        final Cell[][] grid = {{new Cell(false, false, true, true), new Cell(false, true, true, false)},
            {new Cell(true, false, false, true), new Cell(true, true, false, false)}};
        final String renderGrid = Renderer.render(new Maze(grid), List.of(new CoordinatesPair(0, 0)));
        final String expected = "┼─┼─┼" + System.lineSeparator() +
            "│■  │" + System.lineSeparator() +
            "┼ ┼ ┼" + System.lineSeparator() +
            "│   │" + System.lineSeparator() +
            "┼─┼─┼" + System.lineSeparator();
        assertThat(renderGrid).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка решения лабиринта с помощью DFS")
    void DFSTest() {
        final Cell[][] grid = {{new Cell(false, false, false, true), new Cell(false, true, true, false)},
            {new Cell(false, false, false, true), new Cell(true, true, false, false)}};
        final Maze maze = new Maze(grid);
        Solver solver = new DFSSolver();
        List<CoordinatesPair> solution = solver.solve(maze, new CoordinatesPair(0, 0), new CoordinatesPair(1, 0));
        assertThat(solution).isEqualTo(List.of(
            new CoordinatesPair(0, 0),
            new CoordinatesPair(0, 1),
            new CoordinatesPair(1, 1),
            new CoordinatesPair(1, 0)
        ));
    }

    @Test
    @DisplayName("Проверка решения лабиринта с помощью BFS")
    void BFSTest() {
        final Cell[][] grid = {{new Cell(false, false, false, true), new Cell(false, true, true, false)},
            {new Cell(false, false, false, true), new Cell(true, true, false, false)}};
        final Maze maze = new Maze(grid);
        Solver solver = new BFSSolver();
        List<CoordinatesPair> solution = solver.solve(maze, new CoordinatesPair(0, 0), new CoordinatesPair(1, 0));
        assertThat(solution).isEqualTo(List.of(
            new CoordinatesPair(0, 0),
            new CoordinatesPair(0, 1),
            new CoordinatesPair(1, 1),
            new CoordinatesPair(1, 0)
        ));
    }
}
