package year.year2022.day24;

import year.Day;
import year.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day24 implements Day {
    private static Node[][][] valley;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        valley = Parser.parse(file);
        Node[] sources = new Node[valley.length];
        addNodes(sources);
        connectSources(sources);
        connectSourcesToValley(sources);
        Node sink = new Node();
        connectSinkToValley(sink);
        return bfs(sources, sink, 0);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        valley = Parser.parse(file);

        int sum = 0;

        for (int i = 0; i < 3; i++) {
            Node[] sources = new Node[valley.length];
            addNodes(sources);
            connectSources(sources);
            connectSourcesToValley(sources);
            Node sink = new Node();
            connectSinkToValley(sink);
            sum += bfs(sources, sink, sum % valley.length);
            rotateValley180Degrees();
        }

        return sum;
    }

    private static int bfs(Node[] sources, Node sink, int start) {
        Set<Node> visited = new HashSet<>();
        Set<Node> toVisit = new HashSet<>();
        toVisit.add(sources[start]);
        int steps = 0;

        while (true) {
            Set<Node> visitNext = new HashSet<>();

            for (Node node : toVisit) {
                if (node == sink) {
                    return steps;
                }

                visited.add(node);
                visitNext.addAll(node.getNeighbours());
            }

            visitNext.removeAll(visited);
            toVisit = visitNext;
            steps++;
        }
    }

    private void addNodes(Node[] sources) {
        for (int i = 0; i < sources.length; i++) {
            sources[i] = new Node();
        }
    }

    private void connectSources(Node[] sources) {
        for (int i = 0; i < sources.length - 1; i++) {
            sources[i].connect(sources[i+1]);
        }

        sources[sources.length - 1].connect(sources[0]);
    }

    private void connectSourcesToValley(Node[] sources) {
        for (int i = 0; i < valley.length - 1; i++) {
            if (valley[i+1][0][0] != null) {
                sources[i].connect(valley[i+1][0][0]);
            }
        }

        if (valley[0][0][0] != null) {
            sources[sources.length - 1].connect(valley[0][0][0]);
        }
    }

    private void connectSinkToValley(Node sink) {
        for (Node[][] state : valley) {
            if (state[state.length - 1][state[0].length - 1] != null) {
                state[state.length - 1][state[0].length - 1].connect(sink);
            }
        }
    }

    private void rotateValley180Degrees() {
        rotateValley90Degrees();
        rotateValley90Degrees();
    }

    public void rotateValley90Degrees() {
        for (int i = 0; i < valley.length; i++) {
            Node[][] state = valley[i];

            int m = state.length;
            int n = state[0].length;

            Node[][] rotatedState = new Node[n][m];

            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    rotatedState[col][m-row-1] = state[row][col];
                }
            }

            valley[i] = rotatedState;
        }
    }
}
