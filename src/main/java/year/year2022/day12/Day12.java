package year.year2022.day12;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Node> startingNodes = parse(file);
        for (Node node : startingNodes) {
            if (node.value == 'S') {
                return bfs(node);
            }
        }

        throw new NoSuchElementException("S");
    }

    private static int bfs(Node start) {
        Set<Node> visited = new HashSet<>();
        Set<Node> toVisit = new HashSet<>();
        toVisit.add(start);
        int steps = 0;

        while (true) {
            Set<Node> visitNext = new HashSet<>();

            for (Node node : toVisit) {
                if (node.value == 'E')
                    return steps;
                visited.add(node);
                visitNext.addAll(node.neighbours);
            }

            visitNext.removeAll(visited);
            toVisit = visitNext;
            steps++;
        }
    }

    private int bfs(Node start, int limit) {
        Set<Node> visited = new HashSet<>();
        Set<Node> toVisit = new HashSet<>();
        toVisit.add(start);
        int steps = 0;

        while (true) {
            Set<Node> visitNext = new HashSet<>();

            for (Node node : toVisit) {
                if (node.value == 'E')
                    return steps;
                visited.add(node);
                visitNext.addAll(node.neighbours);
            }

            visitNext.removeAll(visited);
            toVisit = visitNext;
            steps++;

            if (steps > limit) {
                return steps;
            }
        }
    }

    private ArrayList<Node> parse(File file) throws FileNotFoundException {
        ArrayList<ArrayList<Character>> mapList = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            ArrayList<Character> cs = new ArrayList<>();
            for (char c : scanner.nextLine().toCharArray())
                cs.add(c);
            mapList.add(cs);
        }

        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int[] numbers = new int[26];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i;

        HashMap<Character, Integer> heights = new HashMap<>();
        for (int i = 0; i < letters.length; i++)
            heights.put(letters[i], numbers[i]);
        heights.put('S', 0);
        heights.put('E', 25);

        Node[][] map = new Node[mapList.size()][mapList.get(0).size()];
        ArrayList<Node> startingNodes = new ArrayList<>();

        for (int row = 0; row < mapList.size(); row++) {
            for (int col = 0; col < mapList.get(row).size(); col++) {
                char letter = mapList.get(row).get(col);
                Node node = new Node(letter, heights.get(letter));
                map[row][col] = node;
                if (letter == 'S' || letter == 'a')
                    startingNodes.add(node);
            }
        }

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Node node = map[row][col];

                if (row > 0) {
                    Node neighbour = map[row-1][col];
                    if (neighbour.height <= node.height + 1)
                        node.connect(neighbour);
                }


                if (row < map.length - 1) {
                    Node neighbour = map[row+1][col];
                    if (neighbour.height <= node.height + 1)
                        node.connect(neighbour);
                }

                if (col > 0) {
                    Node neighbour = map[row][col-1];
                    if (neighbour.height <= node.height + 1)
                        node.connect(neighbour);
                }

                if (col < map[row].length - 1) {
                    Node neighbour = map[row][col+1];
                    if (neighbour.height <= node.height + 1)
                        node.connect(neighbour);
                }
            }
        }

        return startingNodes;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Node> startingNodes = parse(file);
        int[] steps = new int[startingNodes.size()];
        steps[0] = bfs(startingNodes.get(0));
        int min = steps[0];

        for (int i = 1; i < startingNodes.size(); i++) {
            steps[i] = bfs(startingNodes.get(i), min);
            if (steps[i] < min) min = steps[i];
        }

        return min;
    }

    private static class Node {
        char value;
        int height;
        Set<Node> neighbours;

        Node(char value, int height) {
            this.value = value;
            this.height = height;
            this.neighbours = new HashSet<>();
        }

        void connect(Node node) {
            this.neighbours.add(node);
        }
    }
}
