package year.year2024.day02;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static year.Utils.getLines;

public class Day2 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] reports = parse(file);
        int sum = 0;
        for (int[] report : reports) {
            if (isStronglyIncreasing(report)) {
                sum++;
            } else {
                reverse(report);
                if (isStronglyIncreasing(report)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean isStronglyIncreasing(int[] report) {
        for (int i = 1; i < report.length; i++) {
            if (report[i] <= report[i - 1] || report[i] - report[i - 1] > 3) {
                return false;
            }
        }
        return true;
    }

    private void reverse(int[] report) {
        int left = 0;
        int right = report.length - 1;
        while (left < right) {
            int temp = report[left];
            report[left] = report[right];
            report[right] = temp;
            left++;
            right--;
        }
    }

    private int[][] parse(File file) throws FileNotFoundException {
        ArrayList<String> lines = getLines(file);
        int[][] reports = new int[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            int[] report = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                report[j] = Integer.parseInt(split[j]);
            }
            reports[i] = report;
        }

        return reports;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
