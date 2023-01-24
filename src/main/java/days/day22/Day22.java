package days.day22;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Day22 implements Day {
    enum Direction {
        RIGHT(0),
        DOWN(1),
        LEFT(2),
        UP(3);

        private final int val;

        Direction(int val) {
            this.val = val;
        }
    }

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Character[][] board = getBoard(scanner);
        String[] path = getPath(scanner);
        Player player = new Player(getFirstX(board, 0), 0, Direction.RIGHT);

        for (String instruction : path) {
            try {
                int steps = Integer.parseInt(instruction);
                move(board, player, steps);
            } catch (NumberFormatException ignore) {
                turn(player, instruction);
            }
        }

        return 1000 * (player.y + 1) + 4 * (player.x + 1) + player.direction.val;
    }

    private void move(Character[][] board, Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            int oldX = player.x;
            int oldY = player.y;

            player.move();

            if (player.y < 0 || player.y >= board.length
                    || player.x < 0 || player.x >= board[player.y].length
                    || board[player.y][player.x] == ' ') {
                switch (player.direction) {
                    case RIGHT:
                        player.x = getFirstX(board, player.y);
                        break;
                    case DOWN:
                        player.y = getFirstY(board, player.x);
                        break;
                    case LEFT:
                        player.x = getLastX(board, player.y);
                        break;
                    case UP:
                        player.y = getLastY(board, player.x);
                        break;
                }
            }

            if (board[player.y][player.x] == '#') {
                player.x = oldX;
                player.y = oldY;
                return;
            }
        }
    }

    private static int getFirstX(Character[][] board, int y) {
        for (int x = 0; x < board[y].length; x++)
            if (board[y][x] != ' ')
                return x;
        throw new NoSuchElementException("Missing start tile");
    }

    private int getLastX(Character[][] board, int y) {
        for (int x = board[y].length - 1; x >= 0; x--)
            if (board[y][x] != ' ')
                return x;
        throw new NoSuchElementException("Missing start tile");
    }

    private int getFirstY(Character[][] board, int x) {
        for (int y = 0; y < board.length; y++)
            if (board[y][x] != ' ')
                return y;
        throw new NoSuchElementException("Missing start tile");
    }

    private int getLastY(Character[][] board, int x) {
        for (int y = board.length - 1; y >= 0; y--)
            if (board[y][x] != ' ')
                return y;
        throw new NoSuchElementException("Missing start tile");
    }

    private void turn(Player player, String instruction) {
        switch (instruction) {
            case "R":
                player.turnRight();
                break;
            case "L": player.turnLeft();
        }
    }

    private Character[][] getBoard(Scanner scanner) {
        ArrayList<ArrayList<Character>> boardList = new ArrayList<>();
        int maxLength = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty())
                break;
            ArrayList<Character> cs = new ArrayList<>();
            for (char c : line.toCharArray())
                cs.add(c);
            maxLength = Math.max(maxLength, cs.size());
            boardList.add(cs);
        }

        Character[][] board = new Character[boardList.size()][];

        for (int i = 0; i < board.length; i++) {
            ArrayList<Character> cs = boardList.get(i);
            while (cs.size() < maxLength)
                cs.add(' ');
            board[i] = cs.toArray(new Character[0]);
        }

        return board;
    }

    private String[] getPath(Scanner scanner) {
        String line = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            for (int j = i; j < line.length(); j++) {
                if (!Character.isDigit(line.charAt(j))) {
                    list.add(line.substring(i, j));
                    i = j;
                    break;
                }
            }

            list.add(line.substring(i, i + 1));
        }

        return list.toArray(new String[0]);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    private class Player {
        private int x;
        private int y;
        private Direction direction;

        public Player(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void move() {
            switch (direction) {
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case UP:
                    y--;
                    break;
            }
        }

        public void turnRight() {
            switch (direction) {
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
                case UP:
                    direction = Direction.RIGHT;
            }
        }

        public void turnLeft() {
            switch (direction) {
                case RIGHT:
                    direction = Direction.UP;
                    break;
                case UP:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    break;
                case DOWN:
                    direction = Direction.RIGHT;
            }
        }
    }
}
