package year.year2025.day03;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day3 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] banks = Utils.toInts(file);
        int[] values = new int[banks.length];

        for (int i = 0; i < banks.length; i++) {
            int[] bank = banks[i];

            int fst = bank[0];
            int j = 1;
            for (int k = j; k < bank.length - 1; k++) {
                if (bank[k] > fst) {
                    fst = bank[k];
                    j = k + 1;
                }
            }

            int snd = bank[j];
            for (int k = j; k < bank.length; k++) {
                if (bank[k] > snd) {
                    snd = bank[k];
                }
            }

            values[i] = Integer.parseInt(String.valueOf(fst) + snd);
        }
        return Arrays.stream(values).sum();
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        int[][] banks = Utils.toInts(file);
        long[] values = new long[banks.length];

        for (int iBank = 0; iBank < banks.length; iBank++) {
            int[] bank = banks[iBank];
            int[] batteries = new int[12];

            int i = 0;
            for (int iBatt = 0; iBatt < batteries.length; iBatt++) {
                batteries[iBatt] = bank[i];
                for (int j = i + 1; j < bank.length - 11 + iBatt; j++) {
                    if (bank[j] > batteries[iBatt]) {
                        batteries[iBatt] = bank[j];
                        i = j;
                    }
                }
                i++;
            }

            String value = Arrays.stream(batteries).mapToObj(String::valueOf).collect(Collectors.joining());
            values[iBank] = Long.parseLong(value);
            /*for (int k = j; k < bank.length - 1; k++) {
                if (bank[k] > fst) {
                    fst = bank[k];
                    j = k + 1;
                }
            }

            int snd = bank[j];
            for (int k = j; k < bank.length; k++) {
                if (bank[k] > snd) {
                    snd = bank[k];
                }
            }*/

            //values[i] = Long.parseLong(String.valueOf(fst) + snd);
        }
        return Arrays.stream(values).sum();
    }
}
