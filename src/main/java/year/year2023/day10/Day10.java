package year.year2023.day10;

import year.Day;
import year.Node;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static year.Utils.even;
import static year.Utils.toChars;

public class Day10 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] tiles = toChars(file);
        HashMap<Point, Node> pipes = getLoopPipes(tiles);
        return pipes.size() / 2;
    }

    private static HashMap<Point, Node> getLoopPipes(char[][] tiles) {
        HashMap<Point, Node> pipes = getPipes(tiles);
        Node root = getRoot(pipes, tiles);
        Set<Node> loop = getLoopNodes(root);
        pipes.values().retainAll(loop);
        return pipes;
    }

    static HashMap<Point, Node> getPipes(char[][] tiles) {
        HashMap<Point, Node> nodes = new HashMap<>();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x] != '.') {
                    ArrayList<Node> neighbours = getNeighbours(tiles, x, y, nodes);
                    Node node = new Node();

                    for (Node neighbour : neighbours) {
                        node.connect(neighbour);
                        neighbour.connect(node);
                    }

                    nodes.put(new Point(x, y), node);
                }
            }
        }

        return nodes;
    }

    private static ArrayList<Node> getNeighbours(char[][] tiles, int x, int y, HashMap<Point, Node> nodes) {
        ArrayList<Node> neighbours = new ArrayList<>();

        if (x > 0) {
            Point point = new Point(x - 1, y);
            if (westConnector(tiles[y][x])) {
                if (eastConnector(tiles[y][x - 1])) {
                    neighbours.add(nodes.get(point));
                }
            }
        }

        if (y > 0) {
            Point point = new Point(x, y - 1);
            if (northConnector(tiles[y][x])) {
                if (southConnector(tiles[y - 1][x])) {
                    neighbours.add(nodes.get(point));
                }
            }
        }
        return neighbours;
    }

    private static boolean northConnector(char tile) {
        return tile == '|' || tile == 'L' || tile == 'J' || tile == 'S';
    }

    private static boolean westConnector(char tile) {
        return tile == '-' || tile == 'J' || tile == '7' || tile == 'S';
    }

    private static boolean eastConnector(char tile) {
        return tile == '-' || tile == 'L' || tile == 'F' || tile == 'S';
    }

    private static boolean southConnector(char tile) {
        return tile == '|' || tile == '7' || tile == 'F' || tile == 'S';
    }

    static Node getRoot(HashMap<Point, Node> nodes, char[][] tiles) {
        for (Point point : nodes.keySet()) {
            if (tiles[point.getY()][point.getX()] == 'S') {
                return nodes.get(point);
            }
        }

        throw new NoSuchElementException("Missing root node");
    }

    static Set<Node> getLoopNodes(Node node) {
        HashSet<Node> visited = new HashSet<>();
        visited.add(node);

        while (true) {
            boolean circle = true;

            for (Node neighbour : node.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    node = neighbour;
                    circle = false;
                    break;
                }
            }

            if (circle) {
                break;
            }
        }

        return visited;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] tiles = toChars(file);
        HashMap<Point, Node> pipes = getLoopPipes(tiles);

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (!pipes.containsKey(new Point(j, i))) {
                    tiles[i][j] = '.';
                }
            }
        }

        for (char[] row : tiles) {
            int right = countPipesRightSide(row);
            int left = 0;

            for (int i = 0; i < row.length; i++) {
                if (pipe(row[i])) {
                    char c = row[i];

                    if (c == '|') {
                        right--;
                        left++;
                    } else if (c == 'L') {
                        for (int j = i + 1; j < row.length; j++) {
                            char d = row[j];

                            if (d == '7') {
                                right--;
                                left++;
                                i = j;
                                break;
                            } else if (d == 'J') {
                                right -= 2;
                                left += 2;
                                i = j;
                                break;
                            }
                        }

                    } else if (c == 'F') {
                        for (int j = i + 1; j < row.length; j++) {
                            char d = row[j];

                            if (d == '7') {
                                right -= 2;
                                left += 2;
                                i = j;
                                break;
                            } else if (d == 'J') {
                                right--;
                                left++;
                                i = j;
                                break;
                            }
                        }
                    }
                } else if (left == 0 || right == 0){
                    row[i] = 'O';
                } else {
                    if (!even(left) || !even(right)) {
                        row[i] = 'I';
                    } else {
                        row[i] = 'O';
                    }
                }
            }
        }

        for (Point point : pipes.keySet()) {
            tiles[point.getY()][point.getX()] = '#';
        }

        int area = 0;
        for (char[] row : tiles) {
            for (char c : row) {
                if (c == 'I') {
                    area++;
                }
            }
        }

        return area;
    }

    private boolean pipe(char c) {
        return northConnector(c) || westConnector(c) || eastConnector(c) || southConnector(c);
    }

    private static int countPipesRightSide(char[] row) {
        int right = 0;

        for (int i = 0; i < row.length; i++) {
            char c = row[i];

            if (c == '|') {
                right++;
            } else if (c == 'L') {
                for (int j = i + 1; j < row.length; j++) {
                    char d = row[j];

                    if (d == '7') {
                        right++;
                        i = j;
                        break;
                    } else if (d == 'J') {
                        right += 2;
                        i = j;
                        break;
                    }
                }

            } else if (c == 'F') {
                for (int j = i + 1; j < row.length; j++) {
                    char d = row[j];

                    if (d == '7') {
                        right += 2;
                        i = j;
                        break;
                    } else if (d == 'J') {
                        right++;
                        i = j;
                        break;
                    }
                }
            }
        }
        return right;
    }
}
