package year.year2024.day15;

import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle2 extends Day15 {
    private static char[][] warehouse;

    public static int solve(File file) throws FileNotFoundException {
        Input input = parse(file);
        warehouse = input.warehouse;
        ArrayList<Character> directions = input.directions;
        robot = getRobotPosition(warehouse);
        for (char direction : directions) {
            move(robot.x, robot.y, direction);
        }
        return calcGpsCoordinatesSum();
    }

    private static void move(int x, int y, char direction) {
        if (direction == '<' || direction == '>') {
            int newX = direction == '<' ? x - 1 : x + 1;
            if (warehouse[y][newX] == '[' || warehouse[y][newX] == ']') {
                move(newX, y, direction);
            }
            if (warehouse[y][newX] == '.') {
                warehouse[y][newX] = warehouse[y][x];
                warehouse[y][x] = '.';
                x = newX;
            }
            if (warehouse[y][x] == '@') {
                robot = new Point(x, y);
            }
        } else {
            int newY = direction == '^' ? y - 1 : y + 1;
            
            if (warehouse[y][x] == '[') {
                int otherX = x + 1;
                moveBoth(x, y, direction, newY, otherX);
            } else if (warehouse[y][x] == ']') {
                int otherX = x - 1;
                moveBoth(x, y, direction, newY, otherX);
            } else if (warehouse[y][x] == '@') {
                move(x, newY, direction);
                if (warehouse[newY][x] == '.') {
                    warehouse[newY][x] = warehouse[y][x];
                    warehouse[y][x] = '.';
                    robot = new Point(x, newY);
                }
            }
        }
    }

    private static void moveBoth(int x, int y, char direction, int newY, int otherX) {
        char[][] restore = Utils.clone(warehouse);
        move(x, newY, direction);
        move(otherX, newY, direction);
        if (warehouse[newY][x] == '.' && warehouse[newY][otherX] == '.') {
            warehouse[newY][x] = warehouse[y][x];
            warehouse[newY][otherX] = warehouse[y][otherX];
            warehouse[y][x] = '.';
            warehouse[y][otherX] = '.';
        } else {
            warehouse = restore;
        }
    }

    private static int calcGpsCoordinatesSum() {
        int sum = 0;
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[i].length; j++) {
                if (warehouse[i][j] == '[') {
                    sum += 100 * i + j;
                }
            }
        }
        return sum;
    }

    private static Day15.Input parse(File file) throws FileNotFoundException {
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
                char[] line = scanner.nextLine().toCharArray();
                warehouse[i] = new char[line.length * 2];
                for (int j = 0; j < line.length; j++) {
                    if (line[j] == 'O') {
                        warehouse[i][j * 2] = '[';
                        warehouse[i][j * 2 + 1] = ']';
                    } else {
                        warehouse[i][j * 2] = line[j];
                        warehouse[i][j * 2 + 1] = line[j] == '@' ? '.' : line[j];

                    }
                }
            }
            while (scanner.hasNext()) {
                char[] line = scanner.nextLine().toCharArray();
                for (char c : line) {
                    directions.add(c);
                }
            }
        }
        return new Day15.Input(warehouse, directions);
    }
}
