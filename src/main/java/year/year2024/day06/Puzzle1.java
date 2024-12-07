package year.year2024.day06;

public class Puzzle1 {

    public static int solve(char[][] map) {
        markMap(map);
        return countMarked(map);
    }

    private static void markMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char c = map[i][j];

                if (c == '^' || c == '>' || c == 'v' || c == '<') {
                    markMap(map, i, j, c);
                }
            }
        }
    }

    private static void markMap(char[][] map, int i, int j, char c) {
        try {
            while (true) {
                switch (c) {
                    case '^':
                        while (i >= 0) {
                            map[i][j] = 'X';
                            if (map[i - 1][j] == '#') {
                                c = '>';
                                break;
                            }
                            i--;
                        }
                        break;
                    case '>':
                        while (j < map[i].length) {
                            map[i][j] = 'X';
                            if (map[i][j + 1] == '#') {
                                c = 'v';
                                break;
                            }
                            j++;
                        }
                        break;
                    case 'v':
                        while (i < map.length) {
                            map[i][j] = 'X';
                            if (map[i + 1][j] == '#') {
                                c = '<';
                                break;
                            }
                            i++;
                        }
                        break;
                    case '<':
                        while (j >= 0) {
                            map[i][j] = 'X';
                            if (map[i][j - 1] == '#') {
                                c = '^';
                                break;
                            }
                            j--;
                        }
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // done
        }
    }

    private static int countMarked(char[][] map) {
        int sum = 0;

        for (char[] row : map) {
            for (char c : row) {
                if (c == 'X') {
                    sum++;
                }
            }
        }

        return sum;
    }
}
