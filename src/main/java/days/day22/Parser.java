package days.day22;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Parser {
    public static Square getBoard(Scanner scanner) {
        ArrayList<ArrayList<Character>> boardList = new ArrayList<>();
        int maxLength = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                break;
            }

            ArrayList<Character> cs = new ArrayList<>();

            for (char c : line.toCharArray()) {
                cs.add(c);
            }

            maxLength = Math.max(maxLength, cs.size());
            boardList.add(cs);
        }

        Character[][] board = new Character[boardList.size()][];

        for (int i = 0; i < board.length; i++) {
            ArrayList<Character> cs = boardList.get(i);

            while (cs.size() < maxLength) {
                cs.add(' ');
            }

            board[i] = cs.toArray(new Character[0]);
        }

        return new Square(board);
    }

    public static String[] getPath(Scanner scanner) {
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

    public static ExampleCube getExampleCube(Scanner scanner) {
        ArrayList<Character[][]> sides = getSides(scanner);
        return new ExampleCube(sides.toArray(new Character[0][][]));
    }

    public static Cube getInputCube(Scanner scanner) {
        ArrayList<Character[][]> sides = getSides(scanner);
        return new InputCube(sides.toArray(new Character[0][][]));
    }

    private static ArrayList<Character[][]> getSides(Scanner scanner) {
        Character[][] board = getBoard(scanner).getBoard();
        int sideLength = getSideLength(board);
        ArrayList<Character[][]> sides = new ArrayList<>();

        for (int i = 0; i < board.length; i += sideLength) {
            for (int j = 0; j < board[i].length; j += sideLength) {
                if (board[i][j] == ' ') {
                    continue;
                }

                Character[][] side = new Character[sideLength][sideLength];

                for (int k = 0; k < side.length; k++) {
                    System.arraycopy(board[i + k], j, side[k], 0, side[k].length);
                }

                sides.add(side);
            }
        }

        return sides;
    }

    private static int getSideLength(Character[][] board) {
        int minSideLength = board.length;
        int sideLength;

        for (Character[] characters : board) {
            sideLength = 0;

            for (Character character : characters) {
                if (character != ' ') {
                    sideLength++;
                }
            }

            minSideLength = Math.min(minSideLength, sideLength);
        }

        return minSideLength;
    }
}
