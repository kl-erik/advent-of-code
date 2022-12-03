package days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day1 {
    public int puzzle1(File input) throws FileNotFoundException {
        PriorityQueue<Integer> maxes = getMaxes(input);
        return sum(1, maxes);
    }

    public int puzzle2(File input) throws FileNotFoundException {
        PriorityQueue<Integer> maxes = getMaxes(input);
        return sum(3, maxes);
    }

    private PriorityQueue<Integer> getMaxes(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        PriorityQueue<Integer> maxes = new PriorityQueue<>(Comparator.reverseOrder());

        while (scanner.hasNextLine()) {
            int totalCalories = 0;
            String calories;

            while (scanner.hasNextLine() && !(calories = scanner.nextLine()).isEmpty()) {
                totalCalories += Integer.parseInt(calories);
            }

            maxes.add(totalCalories);
        }

        return maxes;
    }

    private int sum(int top, PriorityQueue<Integer> maxes) {
        int sum = 0;

        for (int i = 0; maxes.peek() != null && i < top; i++) {
            sum += maxes.poll();
        }

        return sum;
    }
}
