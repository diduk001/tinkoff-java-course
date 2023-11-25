package edu.project2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFSSolver implements Solver {
    public DFSSolver() {
    }

    @Override
    public List<CoordinatesPair> solve(Maze maze, CoordinatesPair start, CoordinatesPair end) {
        Set<CoordinatesPair> visitedCells = new HashSet<>();
        List<CoordinatesPair> result = new ArrayList<>();
        solveRecursively(start, end, maze, visitedCells, result);
        return result;
    }

    // Returns true if found end cell
    private boolean solveRecursively(
        CoordinatesPair coords,
        CoordinatesPair end,
        Maze maze,
        Set<CoordinatesPair> visited,
        List<CoordinatesPair> path
    ) {
        path.add(coords);
        visited.add(coords);

        if (coords.equals(end)) {
            return true;
        }

        for (CoordinatesPair nextCellCoords : maze.connectedCells(coords)) {
            if (visited.contains(nextCellCoords)) {
                continue;
            }

            if (solveRecursively(nextCellCoords, end, maze, visited, path)) {
                return true;
            }
        }

        path.removeLast();
        return false;
    }
}
