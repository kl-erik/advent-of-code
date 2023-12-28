package year.year2022.day25;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day25 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<ArrayList<Character>> snafus = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            ArrayList<Character> snafu = new ArrayList<>();
            for (char c : scanner.nextLine().toCharArray()) {
                snafu.add(c);
            }
            snafus.add(snafu);
        }

        long sum = 0;

        for (ArrayList<Character> snafu : snafus) {
            sum += toBase10(snafu);
        }

        ArrayList<Character> snafu = toSnafu(sum);

        reverse(snafu);

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : snafu) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    private ArrayList<Character> toSnafu(Long sum) {
        int grade = getGrade(sum);
        ArrayList<Character> snafu = new ArrayList<>();
        for (int i = 0; i < grade; i++) {
            snafu.add('0');
        }

        while (true) {
            int i = 0;
            ArrayList<Character> tmp = new ArrayList<>();

            if (sum >= 0) {
                while (true) {
                    long multiple = (long) Math.pow(5, i);

                    if (multiple + toBase10(tmp) >= sum) {
                        sum -= multiple;
                        snafu.remove(i);
                        snafu.add(i, '1');
                        break;
                    } else if (2 * multiple + toBase10(tmp) >= sum) {
                        sum -= 2 * multiple;
                        snafu.remove(i);
                        snafu.add(i, '2');
                        break;
                    }

                    if (snafu.get(i) == null) {
                        snafu.add('0');
                    }

                    tmp.add('2');
                    i++;
                }
            } else {
                while (true) {
                    long multiple = (long) -Math.pow(5, i);

                    if (multiple + toBase10(tmp) <= sum) {
                        sum -= multiple;
                        snafu.remove(i);
                        snafu.add(i, '-');
                        break;
                    } else if (2 * multiple + toBase10(tmp) <= sum) {
                        sum -= multiple * 2;
                        snafu.remove(i);
                        snafu.add(i, '=');
                        break;
                    }

                    tmp.add(0, '=');
                    i++;
                }
            }

            if (sum == 0) {
                return snafu;
            }

        }
    }

    private int getGrade(Long sum) {
        int i = 0;
        while (Math.pow(5, i) < sum) {
            i++;
        }
        return i;
    }

    private void reverse(ArrayList<Character> snafu) {
        for (int i = 0; i < snafu.size() / 2; i++) {
            Character tmp = snafu.get(snafu.size() - 1 - i);
            snafu.set(snafu.size() - 1 - i, snafu.get(i));
            snafu.set(i, tmp);
        }
    }

    private static long toBase10(ArrayList<Character> snafu) {
        long number = 0;

        for (int i = 0; i < snafu.size(); i++) {
            long multiple = (long) Math.pow(5, snafu.size() - 1 - i);

            switch (snafu.get(i)) {
                case '-':
                    number += multiple * -1;
                    break;
                case '=':
                    number += multiple * -2;
                    break;
                default:
                    number += multiple * Integer.parseInt(String.valueOf(snafu.get(i)));
            }
        }

        return number;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return puzzle1(file);
    }
}
