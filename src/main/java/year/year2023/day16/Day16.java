package year.year2023.day16;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 implements Day {
    enum Direction { RIGHT, LEFT, UP, DOWN }

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] contraption = Utils.toChars(file);
        ArrayList<Beam> beams = new ArrayList<>();
        beams.add(new Beam(0, -1, Direction.RIGHT));
        return calcEnergized(beams, contraption);
    }

    private static int calcEnergized(ArrayList<Beam> beams, char[][] contraption) {
        HashMap<Point, HashSet<Direction>> energized = new HashMap<>();

        while (!beams.isEmpty()) {
            ArrayList<Beam> newBeams = new ArrayList<>();

            Iterator<Beam> iterator = beams.iterator();
            while (iterator.hasNext()) {
                Beam beam = iterator.next();

                beam.move();

                if (isOutside(beam, contraption)
                        || energized.containsKey(beam) && energized.get(beam).contains(beam.direction)) {
                    iterator.remove();
                } else {
                    HashSet<Direction> directions = energized.getOrDefault(beam, new HashSet<>());
                    directions.add(beam.direction);
                    energized.put(new Point(beam.getX(), beam.getY()), directions);

                    switch (contraption[beam.getY()][beam.getX()]) {
                        case '|':
                            if (beam.hasHorizontalDirection()) {
                                newBeams.add(beam.split());
                            }
                            break;
                        case '-':
                            if (beam.hasVerticalDirection()) {
                                newBeams.add(beam.split());
                            }
                            break;
                        case '/':
                            beam.turnForwardsLeaningMirror();
                            break;
                        case '\\':
                            beam.turnBackwardsLeaningMirror();
                            break;
                    }
                }
            }

            beams.addAll(newBeams);
        }

        return energized.size();
    }

    private static boolean isOutside(Beam beam, char[][] contraption) {
        return beam.getY() < 0 || beam.getX() < 0
                || beam.getY() > contraption.length - 1 || beam.getX() > contraption[beam.getY()].length - 1;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] contraption = Utils.toChars(file);
        char[][][] rotations = getRotations(contraption);

        int maxEnergized = 0;

        for (char[][] rotation : rotations) {
            for (int i = 0; i < rotation.length; i++) {
                ArrayList<Beam> beams = new ArrayList<>();
                beams.add(new Beam(i, -1, Direction.RIGHT));
                int energized = calcEnergized(beams, rotation);

                if (energized > maxEnergized) {
                    maxEnergized = energized;
                }
            }
        }

        return maxEnergized;
    }

    private char[][][] getRotations(char[][] contraption) {
        char[][] flipped = Utils.rotate90Degrees(contraption);
        flipMirrorsAndSplitters(flipped);

        char[][][] rotations = new char[4][][];
        rotations[0] = contraption;
        rotations[1] = flipped;
        rotations[2] = Utils.rotate90Degrees(Utils.rotate90Degrees(contraption));
        rotations[3] = Utils.rotate90Degrees(Utils.rotate90Degrees(flipped));
        return rotations;
    }

    private void flipMirrorsAndSplitters(char[][] contraption) {
        for (int i = 0; i < contraption.length; i++) {
            for (int j = 0; j < contraption[i].length; j++) {
                char tile = contraption[i][j];

                if (tile == '|') {
                    contraption[i][j] = '-';
                } else if (tile == '-') {
                    contraption[i][j] = '|';
                } else if (tile == '/') {
                    contraption[i][j] = '\\';
                } else if (tile == '\\') {
                    contraption[i][j] = '/';
                }
            }
        }
    }

    private static class Beam extends Point {
        private Direction direction;

        public Beam(int y, int x, Direction direction) {
            super(x, y);
            this.direction = direction;
        }

        public void move() {
            switch (direction) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
                case LEFT:
                    x--;
                    break;
            }
        }

        public Beam split() {
            switch (direction) {
                case UP:
                case DOWN:
                    direction = Direction.LEFT;
                    return new Beam(y, x, Direction.RIGHT);
                case RIGHT:
                case LEFT:
                    direction = Direction.UP;
                    return new Beam(y, x, Direction.DOWN);
                default:
                    throw new NoSuchElementException();
            }
        }

        public boolean hasHorizontalDirection() {
            return direction == Direction.RIGHT || direction == Direction.LEFT;
        }

        public boolean hasVerticalDirection() {
            return direction == Direction.UP || direction == Direction.DOWN;
        }

        public void turnForwardsLeaningMirror() {
            switch (direction) {
                case UP:
                    direction = Direction.RIGHT;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    direction = Direction.UP;
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    break;
            }
        }

        public void turnBackwardsLeaningMirror() {
            switch (direction) {
                case UP:
                    direction = Direction.LEFT;
                    break;
                case DOWN:
                    direction = Direction.RIGHT;
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
            }
        }
    }
}
