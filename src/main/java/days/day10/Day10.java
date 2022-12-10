package days.day10;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 implements Day {
    static int cycle = 1;
    static int x = 1;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sumSignals = getInteger(scanner, 0, 20, 20);
        return sumSignals + getInteger(scanner, 20, 40, 220);
    }

    private static int getInteger(Scanner scanner, int offset, int mod, int maxIteration) {
        int sumSignals = 0;

        while (scanner.hasNextLine()) {
            String instruction = scanner.nextLine();

            if (!instruction.equals("noop")) {
                int v = Integer.parseInt(instruction.split(" ")[1]);
                cycle++;

                if ((cycle - offset) % mod == 0) {
                    sumSignals += cycle * x;
                }


                x += v;

                if (cycle >= maxIteration) {
                    cycle++;
                    break;
                }
            }

            cycle++;

            if ((cycle - offset) % mod == 0) {
                sumSignals += cycle * x;
            }

            if (cycle >= maxIteration) {
                break;
            }
        }

        return sumSignals;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
