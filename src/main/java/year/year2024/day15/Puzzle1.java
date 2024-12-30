package year.year2024.day15;

import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle1 extends Day15 {
    public static int solve(File file) throws FileNotFoundException {
        Input input = parse(file);
        char[][] warehouse = input.warehouse;
        ArrayList<Character> directions = input.directions;
        robot = getRobotPosition(warehouse);
        for (char direction : directions) {
            move(robot.x, robot.y, direction, warehouse);
        }
        return calcGpsCoordinatesSum(warehouse);
    }

    private static void move(int x, int y, char direction, char[][] warehouse) {
        int newX = direction == '<' ? x - 1 : direction == '>' ? x + 1 : x;
        int newY = direction == '^' ? y - 1 : direction == 'v' ? y + 1 : y;
        if (warehouse[newY][newX] == 'O') {
            move(newX, newY, direction, warehouse);
        }
        if (warehouse[newY][newX] == '.') {
            warehouse[newY][newX] = warehouse[y][x];
            warehouse[y][x] = '.';
            x = newX;
            y = newY;
        }
        if (warehouse[y][x] == '@') {
            robot = new Point(x, y);
        }
    }

    private static int calcGpsCoordinatesSum(char[][] warehouse) {
        int sum = 0;
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[i].length; j++) {
                if (warehouse[i][j] == 'O') {
                    sum += 100 * i + j;
                }
            }
        }
        return sum;
    }

    private static Input parse(File file) throws FileNotFoundException {
        int rows = 0;
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext() && !scanner.nextLine().isEmpty()) {
                rows++;
            }
        }
        char[][] warehouse = new char[rows][];
        ArrayList<Character> directions = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < warehouse.length; i++) {
                warehouse[i] = scanner.nextLine().toCharArray();
            }
            while (scanner.hasNext()) {
                char[] line = scanner.nextLine().toCharArray();
                for (char c : line) {
                    directions.add(c);
                }
            }
        }
        return new Input(warehouse, directions);
    }
}
