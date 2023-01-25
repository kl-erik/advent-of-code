package days.day22;

import java.util.NoSuchElementException;

public class Board {
    private final Character[][] board;

    public Board(Character[][] board) {
        this.board = board;
    }

    public int getFirstX(int y) {
        for (int x = 0; x < board[y].length; x++) {
            if (board[y][x] != ' ') {
                return x;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getLastX(int y) {
        for (int x = board[y].length - 1; x >= 0; x--) {
            if (board[y][x] != ' ') {
                return x;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getFirstY(int x) {
        for (int y = 0; y < board.length; y++) {
            if (board[y][x] != ' ') {
                return y;
            }
        }

        throw new NoSuchElementException("Missing start tile");
    }

    public int getLastY(int x) {
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
}
