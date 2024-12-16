package year.year2024.day10;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day10 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] map = Utils.toInts(file);
        Node[][] nodes = getNodes(map);
        addEdges(map, nodes);
        ArrayList<Node> sources = getSources(map, nodes);
        ArrayList<Node> sinks = getSinks(map, nodes);
        int sum = 0;
        for (Node source : sources) {
            Set<Node> endNodes = bfs(source);
            sum += (int) sinks.stream().filter(endNodes::contains).count();
        }
        return sum;
    }

    private static Set<Node> bfs(Node source) {
        Set<Node> endNodes = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        Set<Node> toVisit = new HashSet<>();
        toVisit.add(source);

        while (!toVisit.isEmpty()) {
            Set<Node> visitNext = new HashSet<>();

            for (Node node : toVisit) {
                visited.add(node);

                if (node.neighbours.isEmpty() || visited.containsAll(node.neighbours)) {
                    endNodes.add(node);
                } else {
                    visitNext.addAll(node.neighbours);
                }
            }

            visitNext.removeAll(visited);
            toVisit = visitNext;
        }

        return endNodes;
    }

    private static Node[][] getNodes(int[][] map) {
        Node[][] nodes = new Node[map.length][map[0].length];
        for (Node[] row : nodes) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new Node();
            }
        }
        return nodes;
    }

    private void addEdges(int[][] map, Node[][] nodes) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = nodes[i][j];
                if (i > 0 && map[i][j] == map[i - 1][j] - 1) {
                    node.neighbours.add(nodes[i - 1][j]);
                }
                if (i < map.length - 1 && map[i][j] == map[i + 1][j] - 1) {
                    node.neighbours.add(nodes[i + 1][j]);
                }
                if (j > 0 && map[i][j] == map[i][j - 1] - 1) {
                    node.neighbours.add(nodes[i][j - 1]);
                }
                if (j < map[i].length - 1 && map[i][j] == map[i][j + 1] - 1) {
                    node.neighbours.add(nodes[i][j + 1]);
                }
            }
        }
    }

    private ArrayList<Node> getSources(int[][] map, Node[][] nodes) {
        return getNodesByValue(map, nodes, 0);
    }

    private ArrayList<Node> getSinks(int[][] map, Node[][] nodes) {
        return getNodesByValue(map, nodes, 9);
    }

    private ArrayList<Node> getNodesByValue(int[][] map, Node[][] nodes, int v) {
        ArrayList<Node> nodesSubset = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == v) {
                    nodesSubset.add(nodes[i][j]);
                }
            }
        }
        return nodesSubset;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    private static class Node {
        private final ArrayList<Node> neighbours;

        public Node() {
            this.neighbours = new ArrayList<>();
        }
    }
}
