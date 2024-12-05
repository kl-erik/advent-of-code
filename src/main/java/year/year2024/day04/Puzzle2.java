package year.year2024.day04;

import year.Point;

import java.util.ArrayList;

import static year.Utils.rotateMatrix90Degrees;

public class Puzzle2 {
    public static int solve(char[][] wordSearch) {
        Letter[][] letterMatrix = toLettersMatrix(wordSearch);
        ArrayList<ArrayList<ArrayList<Letter>>> letterDirections = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            letterDirections.add(getDiagonalLetters(letterMatrix));
            letterMatrix = rotateMatrix90Degrees(letterMatrix);
        }

        ArrayList<Point> aPositions = new ArrayList<>();

        for (ArrayList<ArrayList<Letter>> letterDirection : letterDirections) {
            for (ArrayList<Letter> letters : letterDirection) {
                for (int i = 0; i < letters.size() - 2; i++) {
                    if (letters.get(i).letter == 'M'
                            && letters.get(i + 1).letter == 'A'
                            && letters.get(i + 2).letter == 'S') {
                        aPositions.add(letters.get(i + 1).position);
                        i = i + 2;
                    }
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < aPositions.size(); i++) {
            for (int j = i + 1; j < aPositions.size(); j++) {
                if (aPositions.get(i).equals(aPositions.get(j))) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private static ArrayList<ArrayList<Letter>> getDiagonalLetters(Letter[][] letterMatrix) {
        ArrayList<ArrayList<Letter>> diagonalLetters = new ArrayList<>();
        for (int i = 0; i < letterMatrix.length; i++) {
            ArrayList<Letter> letters = new ArrayList<>();
            for (int j = 0; j < letterMatrix[i].length; j++) {
                if (i + j < letterMatrix.length) {
                    letters.add(letterMatrix[i + j][j]);
                }
            }
            diagonalLetters.add(letters);
        }
        for (int i = 1; i < letterMatrix[0].length; i++) {
            ArrayList<Letter> letters = new ArrayList<>();
            for (int j = 0; j < letterMatrix.length; j++) {
                if (i + j < letterMatrix[0].length) {
                    letters.add(letterMatrix[j][i + j]);
                }
            }
            diagonalLetters.add(letters);
        }
        return diagonalLetters;
    }

    private static Letter[][] toLettersMatrix(char[][] wordSearch) {
        Letter[][] letters = new Letter[wordSearch.length][wordSearch[0].length];

        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                letters[i][j] = new Letter(wordSearch[i][j], new Point(i, j));
            }
        }

        return letters;
    }

    private static class Letter {
        char letter;
        Point position;

        public Letter(char letter, Point position) {
            this.letter = letter;
            this.position = position;
        }
    }
}
