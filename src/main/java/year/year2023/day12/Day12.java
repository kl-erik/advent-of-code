package year.year2023.day12;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Day12 implements Day {
    HashMap<String, Long> combinationsMap = new HashMap<>();

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            int totalCombinations = 0;

            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(" ");
                Stack<String> groups = getGroups(split[0]);
                Stack<Integer> sizes = getSizes(split[1]);
                totalCombinations += (int) findCombinations(groups, sizes);
            }

            return totalCombinations;
        }
    }

    private long findCombinations(Stack<String> groups, Stack<Integer> sizes) {
        if (groups.empty()) {
            return 0;
        }

        String partition = groups.toString() + sizes;

        if (!combinationsMap.containsKey(partition)) {
            long combinations = sizes.size() == 1 ? handleBaseCase(groups, sizes.pop()) : handleStepCase(groups, sizes, sizes.pop());
            combinationsMap.put(partition, combinations);
        }

        return combinationsMap.get(partition);
    }

    private long handleStepCase(Stack<String> groups, Stack<Integer> sizes, int size) {
        Stack<String> brokenGroups = Utils.clone(groups);
        brokenGroups.removeIf(group -> !group.contains("#"));

        if (brokenGroups.size() == sizes.size() + 1) {
            groups = brokenGroups;
        } else if (brokenGroups.size() > sizes.size() + 1) {
            return 0;
        }

        long combinations = 0;

        String group = groups.pop();

        for (int i = 0; i + size <= group.length(); i++) {
            String prev = group.substring(0, i);
            String rest = group.substring(i + size);

            if (prev.endsWith("#")) {
                return combinations;
            } else if (rest.startsWith("#")) {
                continue;
            }

            Stack<String> groupsCopy = Utils.clone(groups);
            Stack<Integer> sizesCopy = Utils.clone(sizes);

            if (rest.length() > 1) {
                groupsCopy.push(rest.substring(1));
            }

            combinations += findCombinations(groupsCopy, sizesCopy);
        }

        sizes.push(size);
        return group.contains("#") ? combinations : combinations + findCombinations(groups, sizes);
    }

    private long handleBaseCase(Stack<String> groups, int size) {
        Stack<String> brokenGroups = Utils.clone(groups);
        brokenGroups.removeIf(group -> !group.contains("#"));

        if (brokenGroups.size() == 1) {
            groups = brokenGroups;
        } else if (brokenGroups.size() > 1) {
            return 0;
        }

        long combinations = 0;

        for (String group : groups) {
            for (int i = 0; i + size <= group.length(); i++) {
                String prev = group.substring(0, i);
                String rest = group.substring(i + size);

                if (prev.endsWith("#")) {
                    return rest.contains("#") ? 0 : combinations;
                } else if (rest.contains("#")) {
                    continue;
                }

                combinations++;
            }
        }

        return combinations;
    }

    private Stack<Integer> getSizes(String sizeList) {
        Stack<Integer> sizes = new Stack<>();
        for (String sizeString : sizeList.split(",")) {
            sizes.push(Integer.parseInt(sizeString));
        }
        Collections.reverse(sizes);
        return sizes;
    }

    private static Stack<String> getGroups(String row) {
        Stack<String> groups = new Stack<>();
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) != '.') {
                int start = i++;

                while (i < row.length() && row.charAt(i) != '.') {
                    i++;
                }

                groups.add(row.substring(start, i));
            }
        }
        Collections.reverse(groups);
        return groups;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            long totalCombinations = 0;

            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(" ");
                Stack<String> groups = getGroups((split[0] + "?").repeat(4) + split[0]);
                Stack<Integer> sizes = getSizes((split[1] + ",").repeat(4) + split[1]);
                totalCombinations += findCombinations(groups, sizes);
            }

            return totalCombinations;
        }
    }
}
