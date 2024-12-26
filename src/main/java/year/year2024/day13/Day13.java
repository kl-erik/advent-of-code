package year.year2024.day13;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static year.year2024.day13.Parser.parse;

public class Day13 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        /*
        ax = A.x
        ay = A.y
        bx = B.x
        by = B.y
        x = prize.x
        y = prize.y
        if x = a1 + b1 && a1 % ax == 0 && b1 % bx == 0
            && y == a2 + b2 && a2 % ay == 0 && b2 % by == 0
            && a1 / ax == a2 / ay && b1 / bx == b2 / by -> a1 / ax A-presses + b1 / bx B-presses
         */
        ArrayList<Machine> machines = parse(file);
        int tokens = 0;
        for (Machine machine : machines) {
            int ax = machine.getA().getX();
            int ay = machine.getA().getY();
            int bx = machine.getB().getX();
            int by = machine.getB().getY();
            int x = machine.getPrize().getX();
            int y = machine.getPrize().getY();

            int minTokens = Integer.MAX_VALUE;

            for (int aPresses = 0; aPresses <= x / ax; aPresses++) {
                for (int bPresses = 0; bPresses <= y / by; bPresses++) {
                    if (aPresses * ax + bPresses * bx == x && aPresses * ay + bPresses * by == y) {
                        minTokens = Math.min(minTokens, aPresses * 3 + bPresses);
                    }
                }
            }

            if (minTokens != Integer.MAX_VALUE) {
                tokens += minTokens;
            }
        }
        return tokens;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
