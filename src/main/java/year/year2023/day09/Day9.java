package year.year2023.day09;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int sum = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            int[] sequence = Utils.toInts(scanner.nextLine().split(" "));
            ArrayList<int[]> sequences = generateSequences(sequence);
            sum += getNextValue(sequences);
        }

        return sum;
    }

    private int getNextValue(ArrayList<int[]> sequences) {
        Collections.reverse(sequences);
        int nextValue = 0;
        for (int[] sequence : sequences) {
            nextValue += sequence[sequence.length - 1];
        }
        return nextValue;
    }

    private ArrayList<int[]> generateSequences(int[] sequence) {
        ArrayList<int[]> sequences = new ArrayList<>();
        sequences.add(sequence);
        while (!allZeroes(sequence)) {
            int[] diffs = new int[sequence.length - 1];

            for (int i = 0; i < diffs.length; i++) {
                diffs[i] = sequence[i+1] - sequence[i];
            }

            sequences.add(diffs);
            sequence = diffs;
        }
        return sequences;
    }

    private boolean allZeroes(int[] sequence) {
        for (int value : sequence) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        int sum = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            int[] sequence = Utils.toInts(scanner.nextLine().split(" "));
            ArrayList<int[]> sequences = generateSequences(sequence);
            sum += getPrevValue(sequences);
        }

        return sum;
    }

    private int getPrevValue(ArrayList<int[]> sequences) {
        Collections.reverse(sequences);
        int prevValue = 0;
        for (int[] sequence : sequences) {
            prevValue = sequence[0] - prevValue;
        }
        return prevValue;
    }
}
