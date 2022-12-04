package days.day3;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Day3 implements Day {
    char[] priorities = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public int puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNextLine()) {
            String items = scanner.nextLine();
            char[] compartment1 = items.substring(0, items.length() / 2).toCharArray();
            char[] compartment2 = items.substring(items.length() / 2).toCharArray();
            char item = findItem(compartment1, compartment2);
            int priority = getPriority(item);
            sum += priority;
        }

        return sum;
    }

    private static char findItem(char[] compartment1, char[] compartment2) {
        Set<Character> visited = new HashSet<>();

        for (char item1 : compartment1) {
            if (!visited.contains(item1)) {
                for (char item2 : compartment2) {
                    if (item1 == item2) {
                        return item1;
                    }
                }

                visited.add(item1);
            }
        }

        throw new NoSuchElementException();
    }

    private int getPriority(char item) {
        char itemLow = Character.toLowerCase(item);

        for (int i = 0; i < priorities.length; i++) {
            if (itemLow == priorities[i]) {
                if (Character.isLowerCase(item)) {
                    return i + 1;
                } else {
                    return i + 27;
                }
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public int puzzle2(File file) throws FileNotFoundException {
        return 0;
    }
}
