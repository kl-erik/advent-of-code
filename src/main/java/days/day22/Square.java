package days.day22;

import java.util.NoSuchElementException;

public class Square extends Board {
    private final Character[][] board;

    public Square(Character[][] board) {
        super();
        this.board = board;
        this.y = 0;
        this.x = getFirstX();
    }

    @Override
    public void move(int steps) {
        for (int step = 0; step < steps; step++) {
            int oldX = x;
            int oldY = y;
            step();

            if (y < 0 || y >= board.length
                    || x < 0 || x >= board[y].length
                    || board[y][x] == ' ') {
                switch (direction) {
                    case RIGHT:
                        x = getFirstX();
                        break;
                    case DOWN:
                        y = getFirstY();
                        break;
                    case LEFT:
                        x = getLastX();
                        break;
                    case UP:
                        y = getLastY();
                        break;
                }
            }

            if (board[y][x] == '#') {
                x = oldX;
                y = oldY;
                return;
            }
        }
    }

    public int getFirstX() {
        for (int x = 0; x < board[y].length; x++) {
            if (board[y][x] != ' ') {
                return x;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getLastX() {
        for (int x = board[y].length - 1; x >= 0; x--) {
            if (board[y][x] != ' ') {
                return x;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getFirstY() {
        for (int y = 0; y < board.length; y++) {
            if (board[y][x] != ' ') {
                return y;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getLastY() {
        for (int y = board.length - 1; y >= 0; y--) {
            if (board[y][x] != ' ') {
                return y;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public Character[][] getBoard() {
        return board;
    }

    @Override
    public int getRow() {
        return y + 1;
    }

    @Override
    public int getColumn() {
        return x + 1;
    }
}
