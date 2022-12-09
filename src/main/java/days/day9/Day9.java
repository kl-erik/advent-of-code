package days.day9;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        return solve(file, 2);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return solve(file, 10);
    }

    private static int solve(File file, int knots) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> directions = new ArrayList<>();
        ArrayList<Integer> steps = new ArrayList<>();
        int maxSize = 0;

        while (scanner.hasNextLine()) {
            String[] motion = scanner.nextLine().split(" ");
            directions.add(motion[0]);
            int step = Integer.parseInt(motion[1]);
            steps.add(step);
            maxSize += step;
        }

        boolean[][] board = new boolean[maxSize * 2][maxSize * 2];
        Position[] positions = new Position[knots];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = new Position(maxSize, maxSize);
        }

        board[positions[positions.length - 1].x][positions[positions.length - 1].y] = true;

        for (int i = 0; i < directions.size(); i++) {
            String direction = directions.get(i);
            int step = steps.get(i);

            switch (direction) {
                case "R":
                    for (int j = 0; j < step; j++) {
                        positions[0].y++;
                        update(positions);
                        board[positions[positions.length - 1].x][positions[positions.length - 1].y] = true;
                    }
                    break;
                case "L":
                    for (int j = 0; j < step; j++) {
                        positions[0].y--;
                        update(positions);
                        board[positions[positions.length - 1].x][positions[positions.length - 1].y] = true;
                    }
                    break;
                case "U":
                    for (int j = 0; j < step; j++) {
                        positions[0].x++;
                        update(positions);
                        board[positions[positions.length - 1].x][positions[positions.length - 1].y] = true;
                    }
                    break;
                case "D":
                    for (int j = 0; j < step; j++) {
                        positions[0].x--;
                        update(positions);
                        board[positions[positions.length - 1].x][positions[positions.length - 1].y] = true;
                    }
                    break;
            }
        }

        int visited = 0;

        for (boolean[] row : board) {
            for (boolean col : row) {
                if (col) {
                    visited++;
                }
            }
        }

        return visited;
    }

    private static void update(Position[] positions) {
        for (int k = 1; k < positions.length; k++) {
            if (!positions[k].canReach(positions[k-1])) {
                if (positions[k-1].y - positions[k].y > 0) {
                    positions[k].y++;
                } else if (positions[k-1].y - positions[k].y < 0) {
                    positions[k].y--;
                }

                if (positions[k-1].x - positions[k].x > 0) {
                    positions[k].x++;
                } else if (positions[k-1].x - positions[k].x < 0) {
                    positions[k].x--;
                }
            } else {
                break;
            }
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean canReach(Position position) {
            return (x == position.x || x - 1 == position.x || x + 1 == position.x)
                && (y == position.y || y - 1 == position.y || y + 1 == position.y);
        }
    }
}
