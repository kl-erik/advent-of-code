package year.year2024.day06;

import year.Utils;

public class Puzzle2 {

    public static int solve(char[][] charMap) {
        String[][] map = toStrings(charMap);
        boolean found = false;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ("^>v<".contains(map[i][j])) {
                    found = true;
                    markAndCheckLoop(map, i, j, map[i][j]);
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        int loops = 0;

        for (String[] row : map) {
            for (String cell : row) {
                if (cell.contains("O")) {
                    loops++;
                }
            }
        }

        return loops;
    }

    private static String[][] toStrings(char[][] map) {
        String[][] stringMap = new String[map.length][];

        for (int i = 0; i < map.length; i++) {
            stringMap[i] = new String[map[i].length];

            for (int j = 0; j < map[i].length; j++) {
                stringMap[i][j] = String.valueOf(map[i][j]);
            }
        }

        return stringMap;
    }

    private static void markAndCheckLoop(String[][] map, int i, int j, String direction) {
        try {
            while (true) {
                switch (direction) {
                    case "^":
                        while (i >= 0) {
                            map[i][j] += direction;

                            if (map[i-1][j].equals("#")) {
                                direction = ">";
                                break;
                            }

                            if (map[i-1][j].equals(".")) {
                                String[][] clone = Utils.clone(map);
                                clone[i-1][j] = "#";
                                if (checkLoop(clone, i, j, ">")) {
                                    map[i-1][j] += "O";
                                }
                            }

                            i--;
                        }
                        break;
                    case ">":
                        while (j < map[i].length) {
                            map[i][j] += direction;

                            if (map[i][j+1].equals("#")) {
                                direction = "v";
                                break;
                            }

                            if (map[i][j+1].equals(".")) {
                                String[][] clone = Utils.clone(map);
                                clone[i][j+1] = "#";
                                if (checkLoop(clone, i, j, "v")) {
                                    map[i][j+1] += "O";
                                }
                            }

                            j++;
                        }
                        break;
                    case "v":
                        while (i < map.length) {
                            map[i][j] += direction;

                            if (map[i+1][j].equals("#")) {
                                direction = "<";
                                break;
                            }

                            if (map[i+1][j].equals(".")) {
                                String[][] clone = Utils.clone(map);
                                clone[i+1][j] = "#";
                                if (checkLoop(clone, i, j, "<")) {
                                    map[i+1][j] += "O";
                                }
                            }

                            i++;
                        }
                        break;
                    case "<":
                        while (j >= 0) {
                            map[i][j] += direction;

                            if (map[i][j-1].equals("#")) {
                                direction = "^";
                                break;
                            }

                            if (map[i][j-1].equals(".")) {
                                String[][] clone = Utils.clone(map);
                                clone[i][j-1] = "#";
                                if (checkLoop(clone, i, j, "^")) {
                                    map[i][j-1] += "O";
                                }
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

    private static boolean checkLoop(String[][] map, int i, int j, String direction) {
        try {
            while (true) {
                switch (direction) {
                    case "^":
                        while (i >= 0) {
                            if (map[i][j].contains(direction)) {
                                return true;
                            }

                            map[i][j] += direction;

                            if (map[i-1][j].equals("#")) {
                                direction = ">";
                                break;
                            }

                            i--;
                        }

                        break;
                    case ">":
                        while (j < map[i].length) {
                            if (map[i][j].contains(direction)) {
                                return true;
                            }

                            map[i][j] += direction;

                            if (map[i][j+1].equals("#")) {
                                direction = "v";
                                break;
                            }

                            j++;
                        }

                        break;
                    case "v":
                        while (i < map.length) {
                            if (map[i][j].contains(direction)) {
                                return true;
                            }

                            map[i][j] += direction;

                            if (map[i+1][j].equals("#")) {
                                direction = "<";
                                break;
                            }

                            i++;
                        }

                        break;
                    case "<":
                        while (j >= 0) {
                            if (map[i][j].contains(direction)) {
                                return true;
                            }

                            map[i][j] += direction;

                            if (map[i][j-1].equals("#")) {
                                direction = "^";
                                break;
                            }

                            j--;
                        }

                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
