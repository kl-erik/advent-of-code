package days.day17;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day17 implements Day {
    enum Shape {
        HORIZONTAL,
        PLUS,
        ANGLE,
        VERTICAL,
        SQUARE
    }

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Integer> heights = getHeights(file, 2022);
        return heights.get(heights.size() - 1);
    }

    private ArrayList<Integer> getHeights(File file, int stones) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        char[] gas = scanner.nextLine().toCharArray();
        Shape[] shapes = Shape.values();
        int gasIndex = 0;
        int shapeIndex = 0;
        int maxHeight = 0;
        ArrayList<boolean[]> chamber = new ArrayList<>();
        ArrayList<boolean[]> latestChamber;
        ArrayList<boolean[]> testChamber;
        Rock rock;
        boolean stopped;
        ArrayList<Integer> maxHeights = new ArrayList<>();

        for (int i = 0; i < stones; i++) {
            pad(chamber, maxHeight);
            latestChamber = clone(chamber);
            rock = new Rock(2, chamber.size() - maxHeight - 3, shapes[shapeIndex]);
            stopped = false;

            while (!stopped) {
                testChamber = tryShift(rock, gas[gasIndex], chamber);
                if (!testChamber.isEmpty()) {
                    latestChamber = testChamber;
                }

                testChamber = tryLower(rock, chamber);
                if (testChamber.isEmpty()) {
                    chamber = latestChamber;
                    stopped = true;
                } else {
                    latestChamber = testChamber;
                }

                gasIndex++;
                gasIndex %= gas.length;
            }

            shapeIndex++;
            shapeIndex %= shapes.length;

            int height = chamber.size() - rock.y;
            if (height > maxHeight) {
                maxHeight = height;
            }

            maxHeights.add(maxHeight);
        }

        return maxHeights;
    }

    private void pad(ArrayList<boolean[]> chamber, int height) {
        while (chamber.size() < 7 + height) {
            chamber.add(0, new boolean[7]);
        }
    }

    private ArrayList<boolean[]> tryShift(Rock rock, char gas, ArrayList<boolean[]> chamber) {
        int x = rock.x;

        if (gas == '>') {
            rock.x++;
        } else if (gas == '<') {
            rock.x--;
        }

        ArrayList<boolean[]> testChamber = test(rock, chamber);

        if (testChamber.isEmpty()) {
            rock.x = x;
        }

        return testChamber;
    }

    private ArrayList<boolean[]> tryLower(Rock rock, ArrayList<boolean[]> chamber) {
        rock.y++;

        ArrayList<boolean[]> testChamber = test(rock, chamber);

        if (testChamber.isEmpty()) {
            rock.y--;
        }

        return testChamber;
    }

    private ArrayList<boolean[]> test(Rock rock, ArrayList<boolean[]> chamber) {
        ArrayList<boolean[]> testChamber = clone(chamber);
        boolean[][] shape = rock.shape;

        for (int i = rock.y; i < rock.y + shape.length; i++) {
            for (int j = rock.x; j < rock.x + shape[i - rock.y].length; j++) {
                if (i >= testChamber.size() || j < 0 || j >= testChamber.get(i).length || testChamber.get(i)[j] && shape[i - rock.y][j - rock.x]) {
                    return new ArrayList<>();
                } else {
                    testChamber.get(i)[j] = shape[i - rock.y][j - rock.x] || testChamber.get(i)[j];
                }
            }
        }

        return testChamber;
    }

    private ArrayList<boolean[]> clone(ArrayList<boolean[]> list) {
        ArrayList<boolean[]> clone = new ArrayList<>();

        for (boolean[] booleans : list) {
            boolean[] booleansCopy = new boolean[booleans.length];
            System.arraycopy(booleans, 0, booleansCopy, 0, booleans.length);
            clone.add(booleansCopy);
        }

        return clone;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Integer> heights = getHeights(file, 10000);
        int period = findCycle(heights);
        heights = getHeights(file, period * 2);
        int init = heights.get(period - 1); // height of the first iteration of period
        int cycle = heights.get(period * 2 - 1) - init; // height difference of subsequent iterations of period
        long rocks = 1000000000000L;
        int rest = (int) (rocks % period);
        rocks -= period + rest;
        heights = getHeights(file, period + rest);
        int end = heights.get(heights.size() - 1) - init;
        return rocks / period * cycle + init + end;
    }

    /*
     * Find x such that for every x stones, the height difference is the same at least 4 times in a row.
     * First iteration starts flat which might be wrong so skip it by setting i = x and j = x*2 instead of i=0 and j=x
     */
    private int findCycle(ArrayList<Integer> maxHeights) {
        int period = 1;

        while (period < maxHeights.size() / 2) {
            int i = period;
            int j = period * 2;
            int heightDifference = maxHeights.get(j) - maxHeights.get(i);
            int streak = 0;

            while (j < maxHeights.size() && maxHeights.get(j) - maxHeights.get(i) == heightDifference) {
                streak++;
                i = j;
                j += period;
            }

            if (streak >= 4) {
                return period;
            }

            period++;
        }

        return -1;
    }

    static class Rock {
        int x;
        int y;
        final boolean[][] shape;

        public Rock(int x, int y, Shape shape) {
            this.shape = getShape(shape);
            this.x = x;
            this.y = y - this.shape.length;
        }

        private boolean[][] getShape(Shape shape) {
            switch (shape) {
                case HORIZONTAL:
                    return new boolean[][]{
                            {true, true, true, true}
                    };
                case PLUS:
                    return new boolean[][]{
                            {false, true, false},
                            {true, true, true},
                            {false, true, false}
                    };
                case ANGLE:
                    return new boolean[][]{
                            {false, false, true},
                            {false, false, true},
                            {true, true, true}
                    };
                case VERTICAL:
                    return new boolean[][]{
                            {true},
                            {true},
                            {true},
                            {true}
                    };
                case SQUARE:
                    return new boolean[][]{
                            {true, true},
                            {true, true}
                    };
            }

            throw new IllegalStateException("Unexpected value: " + shape);
        }
    }
}
