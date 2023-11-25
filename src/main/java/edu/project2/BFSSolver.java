package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFSSolver implements Solver {
    public BFSSolver() {
    }

    @Override
    public List<CoordinatesPair> solve(Maze maze, CoordinatesPair start, CoordinatesPair end) {
        Set<CoordinatesPair> visitedCells = new HashSet<>();
        Map<CoordinatesPair, CoordinatesPair> ancestor = new HashMap<>();
        ArrayDeque<CoordinatesPair> queue = new ArrayDeque<>();

        visitedCells.add(start);
        queue.addLast(start);
        while (!queue.isEmpty()) {
            CoordinatesPair curPair = queue.poll();
            if (curPair.equals(end)) {
                break;
            }

            for (CoordinatesPair nextCellCoords : maze.connectedCells(curPair)) {
                if (visitedCells.contains(nextCellCoords)) {
                    continue;
                }
                visitedCells.add(nextCellCoords);
                ancestor.put(nextCellCoords, curPair);
                queue.addLast(nextCellCoords);
            }
        }

        List<CoordinatesPair> result = new ArrayList<>();
        CoordinatesPair curPair = end;
        result.add(curPair);
        while (curPair != start) {
            curPair = ancestor.get(curPair);
            result.add(curPair);
        }
        return result.reversed();
    }
}
