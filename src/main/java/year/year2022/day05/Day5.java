package year.year2022.day05;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Day5 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Stack<Character>> config = parseConfig(scanner);

        while (scanner.hasNextLine()) {
            String[] parsedProcedure = scanner.nextLine().split("move | from | to ");
            int move = Integer.parseInt(parsedProcedure[1]);
            int from = Integer.parseInt(parsedProcedure[2]) - 1;
            int to = Integer.parseInt(parsedProcedure[3]) - 1;
            updateConfig9000(config, move, from, to);
        }

        return getTopString(config);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Stack<Character>> config = parseConfig(scanner);

        while (scanner.hasNextLine()) {
            String[] parsedProcedure = scanner.nextLine().split("move | from | to ");
            int move = Integer.parseInt(parsedProcedure[1]);
            int from = Integer.parseInt(parsedProcedure[2]) - 1;
            int to = Integer.parseInt(parsedProcedure[3]) - 1;
            updateConfig9001(config, move, from, to);
        }

        return getTopString(config);
    }

    private ArrayList<Stack<Character>> parseConfig(Scanner scanner) {
        ArrayList<char[]> board = getBoard(scanner);
        char[] stackNumbers = board.remove(board.size() - 1);
        ArrayList<Stack<Character>> config = parseConfig((stackNumbers.length + 2) / 4);
        fillConfig(config, board);
        return config;
    }

    private static ArrayList<char[]> getBoard(Scanner scanner) {
        ArrayList<char[]> board = new ArrayList<>();
        char[] line;
        while ((line = scanner.nextLine().toCharArray()).length != 0)
            board.add(line);
        return board;
    }

    private static ArrayList<Stack<Character>> parseConfig(int length) {
        ArrayList<Stack<Character>> config = new ArrayList<>();
        for (int i = 0; i < length; i++)
            config.add(new Stack<>());
        return config;
    }

    private void fillConfig(ArrayList<Stack<Character>> config, ArrayList<char[]> board) {
        for (int i = board.size() - 1; i >= 0; i--) {
            char[] row = board.get(i);

            for (int j = 1; j < row.length - 1; j += 4) {
                if (Character.isLetter(row[j])) {
                    config.get((j - 1) / 4).push(row[j]);
                }
            }
        }
    }

    private void updateConfig9000(ArrayList<Stack<Character>> config, int move, int from, int to) {
        Stack<Character> fromStack = config.get(from);
        Stack<Character> toStack = config.get(to);
        for (int i = 0; i < move; i++)
            toStack.push(fromStack.pop());
    }

    private void updateConfig9001(ArrayList<Stack<Character>> config, int move, int from, int to) {
        Stack<Character> fromStack = config.get(from);
        Stack<Character> tempStack = new Stack<>();
        Stack<Character> toStack = config.get(to);
        for (int i = 0; i < move; i++)
            tempStack.push(fromStack.pop());
        for (int i = 0; i < move; i++)
            toStack.push(tempStack.pop());
    }

    private static String getTopString(ArrayList<Stack<Character>> config) {
        StringBuilder top = new StringBuilder();
        for (Stack<Character> stack : config)
            top.append(stack.peek());
        return top.toString();
    }
}
