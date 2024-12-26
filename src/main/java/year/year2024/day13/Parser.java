package year.year2024.day13;

import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static ArrayList<Machine> parse(File file) throws FileNotFoundException {
        ArrayList<Machine> machines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        Pattern pattern = Pattern.compile("X[+=](?<x>\\d+), Y[+=](?<y>\\d+)");
        while (scanner.hasNextLine()) {
            String aLine = scanner.nextLine();
            if (aLine.isEmpty()) continue;
            String bLine = scanner.nextLine();
            String prizeLine = scanner.nextLine();
            Matcher aMatcher = pattern.matcher(aLine);
            Matcher bMatcher = pattern.matcher(bLine);
            Matcher prizeMatcher = pattern.matcher(prizeLine);
            if (aMatcher.find() && bMatcher.find() && prizeMatcher.find()) {
                Point a = new Point(Integer.parseInt(aMatcher.group("x")), Integer.parseInt(aMatcher.group("y")));
                Point b = new Point(Integer.parseInt(bMatcher.group("x")), Integer.parseInt(bMatcher.group("y")));
                Point prize = new Point(Integer.parseInt(prizeMatcher.group("x")), Integer.parseInt(prizeMatcher.group("y")));
                machines.add(new Machine(a, b, prize));
            }
        }
        return machines;
    }
}
