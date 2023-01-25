package days.day22;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public Board getBoard(Scanner scanner) {
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

        return new Board(board);
    }

    public String[] getPath(Scanner scanner) {
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
}
