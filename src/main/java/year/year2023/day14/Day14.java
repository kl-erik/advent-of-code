package year.year2023.day14;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;

public class Day14 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] platform = Utils.toMatrix(file);
        tiltNorth(platform);
        return calcTotalLoad(platform);
    }

    private static void tiltNorth(char[][] platform) {
        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[i].length; j++) {
                if (platform[i][j] == 'O') {
                    platform[i][j] = '.';
                    int k = i;

                    while (k > 0 && platform[k-1][j] == '.') {
                        k--;
                    }

                    platform[k][j] = 'O';
                }
            }
        }
    }

    private int calcTotalLoad(char[][] platform) {
        int sum = 0;

        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[i].length; j++) {
                if (platform[i][j] == 'O') {
                    sum += platform.length - i;
                }
            }
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
