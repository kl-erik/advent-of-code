package year.year2022.day23;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day23 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Groove groove = Parser.parse(file);
        Elf[] elves = getElves(groove);
        char[] directions = {'N', 'S', 'W', 'E'};

        for (int round = 1; round <= 10; round++) {
            doFirstHalf(groove, elves, directions);
            doSecondHalf(groove, elves);
            rotate(directions);
        }

        return getEmptyTiles(groove, elves);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Groove groove = Parser.parse(file);
        Elf[] elves = getElves(groove);
        char[] directions = {'N', 'S', 'W', 'E'};

        int rounds = 1;
        while (true) {
            doFirstHalf(groove, elves, directions);
            if (!doSecondHalf(groove, elves)) return rounds;
            rotate(directions);
            rounds++;
        }
    }

    private void doFirstHalf(Groove groove, Elf[] elves, char[] directions) {
        for (Elf elf : elves) {
            if (groove.hasAdjacentElf(elf)) {
                proposePosition(groove, elf, directions);
            }
        }
    }

    private void rotate(char[] directions) {
        char end = directions[0];
        for (int i = 0; i < directions.length - 1; i++) {
            directions[i] = directions[i + 1];
        }
        directions[directions.length - 1] = end;
    }

    private void proposePosition(Groove groove, Elf elf, char[] directions) {
        for (char direction : directions) {
            switch (direction) {
                case 'N':
                    if (!groove.hasAdjacentElfNorth(elf)) {
                        elf.nextY = elf.y - 1;
                        elf.nextX = elf.x;
                        return;
                    }
                    break;
                case 'S':
                    if (!groove.hasAdjacentElfSouth(elf)) {
                        elf.nextY = elf.y + 1;
                        elf.nextX = elf.x;
                        return;
                    }
                    break;
                case 'W':
                    if (!groove.hasAdjacentElfWest(elf)) {
                        elf.nextY = elf.y;
                        elf.nextX = elf.x - 1;
                        return;
                    }
                    break;
                case 'E':
                    if (!groove.hasAdjacentElfEast(elf)) {
                        elf.nextY = elf.y;
                        elf.nextX = elf.x + 1;
                        return;
                    }
            }
        }

        elf.nextY = elf.y;
        elf.nextX = elf.x;
    }

    private static boolean doSecondHalf(Groove groove, Elf[] elves) {
        for (Elf elf : elves) {
            if (elf.nextY != elf.y || elf.nextX != elf.x) {
                tryMove(groove, elf);
            }
        }

        int moves = 0;

        for (Elf elf : elves) {
            if (elf.nextY != elf.y || elf.nextX != elf.x) {
                move(groove, elf);
                moves++;
            }
        }

        return moves != 0;
    }

    private static void tryMove(Groove groove, Elf elf) {
        if (groove.getElves()[elf.nextY][elf.nextX] == null) {
            groove.getElves()[elf.nextY][elf.nextX] = elf;
        } else {
            Elf other = groove.getElves()[elf.nextY][elf.nextX];
            groove.getElves()[elf.nextY][elf.nextX] = null;
            elf.nextY = elf.y;
            elf.nextX = elf.x;
            other.nextY = other.y;
            other.nextX = other.x;
        }
    }

    private static void move(Groove groove, Elf elf) {
        groove.getElves()[elf.y][elf.x] = null;
        groove.getElves()[elf.nextY][elf.nextX] = elf;
        elf.y = elf.nextY;
        elf.x = elf.nextX;
    }

    private static int getEmptyTiles(Groove groove, Elf[] elves) {
        int minY = elves[0].y;
        int maxY = elves[0].y;
        int minX = elves[0].x;
        int maxX = elves[0].x;

        for (int i = 1; i < elves.length; i++) {
            Elf elf = elves[i];

            if (minY > elf.y) {
                minY = elf.y;
            } else if (maxY < elf.y) {
                maxY = elf.y;
            }

            if (minX > elf.x) {
                minX = elf.x;
            } else if (maxX < elf.x) {
                maxX = elf.x;
            }
        }

        int emptyTiles = 0;

        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                if (groove.getElves()[i][j] == null) {
                    emptyTiles++;
                }
            }
        }

        return emptyTiles;
    }

    private Elf[] getElves(Groove groove) {
        ArrayList<Elf> elves = new ArrayList<>();

        for (int i = 0; i < groove.getElves().length; i++) {
            for (int j = 0; j < groove.getElves()[i].length; j++) {
                if (groove.getElves()[i][j] != null) {
                    Elf elf = groove.getElves()[i][j];
                    elf.y = i;
                    elf.x = j;
                    elves.add(elf);
                }
            }
        }

        return elves.toArray(new Elf[0]);
    }
}
