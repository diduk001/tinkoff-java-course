package edu.project2;

import java.util.List;

public interface Solver {
    List<CoordinatesPair> solve(Maze maze, CoordinatesPair start, CoordinatesPair end);
}
