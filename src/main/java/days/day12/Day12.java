package days.day12;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Node start = parse(file);
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

    private Node parse(File file) throws FileNotFoundException {
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
        Node start = null;

        for (int row = 0; row < mapList.size(); row++) {
            for (int col = 0; col < mapList.get(row).size(); col++) {
                char letter = mapList.get(row).get(col);
                Node node = new Node(letter, heights.get(letter));
                map[row][col] = node;
                if (letter == 'S')
                    start = node;
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

        return start;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
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
