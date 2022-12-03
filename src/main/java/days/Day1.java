package days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public int puzzle1(File input) {
        try (Scanner scanner = new Scanner(input)) {
            int max = 0;

            while (scanner.hasNextLine()) {
                int total = 0;
                String calories;

                while (scanner.hasNextLine() && !(calories = scanner.nextLine()).isEmpty()) {
                    total += Integer.parseInt(calories);
                }

                max = Math.max(max, total);
            }

            return max;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
