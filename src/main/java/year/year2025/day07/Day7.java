package year.year2025.day07;

import year.Day;
import year.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static year.Utils.toChars;

public class Day7 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] matrix = toChars(file);
        int splits = 0;
        for (int row = 1; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row-1][col] == 'S') {
                    matrix[row][col] = '|';
                } else if (matrix[row-1][col] == '|' && matrix[row][col] == '.') {
                    matrix[row][col] = '|';
                } else if (matrix[row-1][col] == '|' && matrix[row][col] == '^') {
                    matrix[row][col-1] = '|';
                    matrix[row][col+1] = '|';
                    splits++;
                }
            }
        }
        return splits;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] matrix = toChars(file);
        Node root = new Node();
        Node[][] nodes = new Node[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row += 2) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'S') {
                    nodes[row][col] = root;
                } else if (matrix[row][col] == '^') {
                    nodes[row][col] = new Node();
                    for (int i = row - 2; i >= 0; i -= 2) {
                        if (matrix[i][col] == '^') {
                            break;
                        }
                        if (matrix[i][col] == 'S') {
                            nodes[i][col].connect(nodes[row][col]);
                        }
                        if (matrix[i][col-1] == '^') {
                            nodes[i][col-1].connect(nodes[row][col]);
                        }
                        if (matrix[i][col+1] == '^') {
                            nodes[i][col+1].connect(nodes[row][col]);
                        }
                    }
                }
            }
        }
        for (Node[] row : nodes) {
            for (Node node : row) {
                if (node != null) {
                    paths.put(node, 0L);
                }
            }
        }
        return dfs(root) - 1;
    }

    private final Map<Node, Long> paths = new HashMap<>();

    private long dfs(Node node) {
        for (Node neighbour : node.getNeighbours()) {
            if (paths.get(neighbour) > 0) {
                paths.put(node, paths.get(node) + paths.get(neighbour));
            } else {
                paths.put(node, paths.get(node) + dfs(neighbour));
            }
        }
        paths.put(node, paths.get(node) + (2 - node.getNeighbours().size()));
        return paths.get(node);
    }
}
