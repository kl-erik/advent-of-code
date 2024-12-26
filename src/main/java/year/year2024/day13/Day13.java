package year.year2024.day13;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 implements Day {
    Integer[][] dp;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Machine> machines = parse(file);
        int tokens = 0;
        for (Machine machine : machines) {
            dp = new Integer[machine.prize.getY() + 1][machine.prize.getX() + 1];
            int result = opt(0, 0, machine);
            if (result != Integer.MAX_VALUE) tokens += result;
        }
        return tokens;
    }

    private int opt(int j, int i, Machine machine) {
        if (j > machine.prize.getX() || i > machine.prize.getY()) return Integer.MAX_VALUE;
        if (j == machine.prize.getX() && i == machine.prize.getY()) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int a = opt(j + machine.a.getX(), i + machine.a.getY(), machine);
        if (a != Integer.MAX_VALUE) a += 3;
        int b = opt(j + machine.b.getX(), i + machine.b.getY(), machine);
        if (b != Integer.MAX_VALUE) b += 1;
        return dp[i][j] = Math.min(a, b);
    }

    private static ArrayList<Machine> parse(File file) throws FileNotFoundException {
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

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    private static class Machine {
        private final Point a, b, prize;

        public Machine(Point a, Point b, Point prize) {
            this.a = a;
            this.b = b;
            this.prize = prize;
        }
    }
}
