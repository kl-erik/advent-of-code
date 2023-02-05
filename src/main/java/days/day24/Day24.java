package days.day24;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day24 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Parser.Pair pair = Parser.parse(file);
        Node start = pair.starts[0];
        Node[] ends = pair.ends;
        return bfs(start, ends);
    }

    private static int bfs(Node start, Node[] ends) {
        Set<Node> visited = new HashSet<>();
        Set<Node> toVisit = new HashSet<>();
        toVisit.add(start);
        int steps = 0;

        while (true) {
            Set<Node> visitNext = new HashSet<>();

            for (Node node : toVisit) {
                if (contains(node, ends))
                    return steps;
                visited.add(node);
                visitNext.addAll(node.getNeighbours());
            }

            visitNext.removeAll(visited);
            toVisit = visitNext;
            steps++;
        }
    }

    private static boolean contains(Node node, Node[] ends) {
        for (Node end : ends) {
            if (node == end) return true;
        }
        return false;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
