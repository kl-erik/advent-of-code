package days.day9;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
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
        Position head = new Position(maxSize, maxSize);
        Position tail = new Position(maxSize, maxSize);
        board[tail.x][tail.y] = true;

        for (int i = 0; i < directions.size(); i++) {
            String direction = directions.get(i);
            int step = steps.get(i);

            switch (direction) {
                case "R":
                    updateX(board, head, tail, step, true);
                    break;
                case "L":
                    updateX(board, head, tail, step, false);
                    break;
                case "U":
                    updateY(board, head, tail, step, true);
                    break;
                case "D":
                    updateY(board, head, tail, step, false);
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

    private void updateY(boolean[][] board, Position head, Position tail, int step, boolean up) {
        int stepDirection = up ? 1 : -1;

        for (int i = 0; i < step; i++) {
            Position previousHead = new Position(head.x, head.y);
            head.y += stepDirection;

            if (!tail.canReach(head)) {
                tail.x = previousHead.x;
                tail.y = previousHead.y;
                board[tail.x][tail.y] = true;
            }
        }
    }

    private static void updateX(boolean[][] board, Position head, Position tail, int step, boolean right) {
        int stepDirection = right ? 1 : -1;

        for (int i = 0; i < step; i++) {
            Position previousHead = new Position(head.x, head.y);
            head.x += stepDirection;

            if (!tail.canReach(head)) {
                tail.x = previousHead.x;
                tail.y = previousHead.y;
                board[tail.x][tail.y] = true;
            }

        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
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
