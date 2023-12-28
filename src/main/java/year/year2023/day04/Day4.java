package year.year2023.day04;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(":\\s+| \\| ");
            ArrayList<String> winningNumbers = new ArrayList<>(Arrays.asList(split[1].split("\\s+")));
            ArrayList<String> numbers = new ArrayList<>(Arrays.asList(split[2].split("\\s+")));
            numbers.retainAll(winningNumbers);
            sum += numbers.size() == 1 ? 1 : (int) Math.pow(2, numbers.size() - 1);
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        HashMap<Integer, Integer> cards = new HashMap<>();

        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(":\\s+| \\| ");
            int card = Integer.parseInt(split[0].split("\\s+")[1]);
            int n = cards.getOrDefault(card, 0) + 1;
            cards.put(card, n);
            ArrayList<String> winningNumbers = new ArrayList<>(Arrays.asList(split[1].split("\\s+")));
            ArrayList<String> numbers = new ArrayList<>(Arrays.asList(split[2].split("\\s+")));
            numbers.retainAll(winningNumbers);

            for (int i = card + 1; i < numbers.size() + card + 1; i++) {
                int m = cards.getOrDefault(i, 0);
                cards.put(i, m + n);
            }

        }

        return cards.values().stream().reduce(0, Integer::sum);
    }
}
