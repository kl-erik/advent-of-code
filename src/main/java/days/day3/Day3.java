package days.day3;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 implements Day {
    char[] priorities = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNextLine()) {
            String rucksack = scanner.nextLine();
            Set<Character> compartment1 = toSet(rucksack.substring(0, rucksack.length() / 2));
            Set<Character> compartment2 = toSet(rucksack.substring(rucksack.length() / 2));
            compartment1.retainAll(compartment2);
            char item = new ArrayList<>(compartment1).get(0);
            int priority = getPriority(item);
            sum += priority;
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNextLine()) {
            Set<Character> rucksack1 = toSet(scanner.nextLine());
            Set<Character> rucksack2 = toSet(scanner.nextLine());
            Set<Character> rucksack3 = toSet(scanner.nextLine());
            rucksack1.retainAll(rucksack2);
            rucksack1.retainAll(rucksack3);
            char item = new ArrayList<>(rucksack1).get(0);
            int priority = getPriority(item);
            sum += priority;
        }

        return sum;
    }

    private Set<Character> toSet(String string) {
        Set<Character> set = new HashSet<>();

        for (char c : string.toCharArray())
            set.add(c);

        return set;
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
}
