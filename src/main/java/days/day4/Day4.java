package days.day4;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 implements Day {
    @Override
    public int puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int fullOverlaps = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pair = line.split(",");
            String[] range1 = pair[0] .split("-");
            String[] range2 = pair[1] .split("-");
            int sectionStart1 = Integer.parseInt(range1[0]);
            int sectionEnd1 = Integer.parseInt(range1[1]);
            int sectionStart2 = Integer.parseInt(range2[0]);
            int sectionEnd2 = Integer.parseInt(range2[1]);

            if (sectionStart1 == sectionStart2 || sectionEnd1 == sectionEnd2 ||
                    sectionStart1 < sectionStart2 && sectionEnd1 > sectionEnd2 ||
                    sectionStart2 < sectionStart1 && sectionEnd2 > sectionEnd1) {
                fullOverlaps++;
            }
        }

        return fullOverlaps;
    }

    @Override
    public int puzzle2(File file) throws FileNotFoundException {
        return 0;
    }
}
