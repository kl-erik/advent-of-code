package year.year2022.day04;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int fullOverlaps = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pair = line.split(",");
            Section section1 = getSection(pair[0]);
            Section section2 = getSection(pair[1]);

            if (section1.start == section2.start || section1.end == section2.end ||
                    section1.start < section2.start && section1.end > section2.end ||
                    section2.start < section1.start && section2.end > section1.end)
                fullOverlaps++;
        }

        return fullOverlaps;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int fullOverlaps = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pair = line.split(",");
            Section section1 = getSection(pair[0]);
            Section section2 = getSection(pair[1]);

            if (section1.start <= section2.start && section1.end >= section2.start ||
            section2.start <= section1.start && section2.end >= section1.start)
                fullOverlaps++;
        }

        return fullOverlaps;
    }

    private Section getSection(String range) {
        String[] endpoints = range.split("-");
        int start = Integer.parseInt(endpoints[0]);
        int end = Integer.parseInt(endpoints[1]);
        return new Section(start, end);
    }

    private static class Section {
        private final int start;
        private final int end;

        public Section(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
