package year.year2025.day01;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        if (line.equals("hej")) {
            return 0;
        }
        return 1;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        if (line.equals("hej")) {
            return 0;
        }
        return 1;
    }
}
