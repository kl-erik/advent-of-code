package year.year2023.day02;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Day2 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(": ");
            String game = split[0];
            String rounds = split[1];
            boolean possible = true;

            for (String hand : rounds.split("; ")) {
                int red = 0, green = 0, blue = 0;

                for (String colors : hand.split(", ")) {
                    String[] tmp = colors.split(" ");

                    switch (tmp[1]) {
                        case "red":
                            red += Integer.parseInt(tmp[0]);
                            break;
                        case "green":
                            green += Integer.parseInt(tmp[0]);
                            break;
                        case "blue":
                            blue += Integer.parseInt(tmp[0]);
                            break;
                        default:
                            throw new NoSuchElementException();
                    }
                }

                if (!(red <= 12 && green <= 13 && blue <= 14)) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                sum += Integer.parseInt(game.split(" ")[1]);
            }
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(": ");
            String rounds = split[1];
            int red = 0, green = 0, blue = 0;

            for (String hand : rounds.split("; ")) {
                for (String colors : hand.split(", ")) {
                    String[] tmp = colors.split(" ");
                    int n = Integer.parseInt(tmp[0]);

                    switch (tmp[1]) {
                        case "red":
                            if (n > red) {
                                red = n;
                            }

                            break;
                        case "green":
                            if (n > green) {
                                green = n;
                            }

                            break;
                        case "blue":
                            if (n > blue) {
                                blue = n;
                            }

                            break;
                        default:
                            throw new NoSuchElementException();
                    }
                }
            }

            sum += red * green * blue;
        }

        return sum;
    }
}
