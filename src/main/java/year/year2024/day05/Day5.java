package year.year2024.day05;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 implements Day {
    HashMap<Integer, Set<Integer>> rules = new HashMap<>();

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> pageList = SetUpRulesAndGetPages(file);

        int sum = 0;

        for (ArrayList<Integer> pages : pageList) {
            ArrayList<Integer> sorted = new ArrayList<>(pages);
            sorted.sort((t1, t2) -> {
                if (rules.containsKey(t1) && rules.get(t1).contains(t2)) {
                    return -1;
                } else if (rules.containsKey(t2) && rules.get(t2).contains(t1)) {
                    return 1;
                } else {
                    return 0;
                }
            });
            if (sorted.equals(pages)) {
                sum += pages.get(pages.size() % 2 == 0 ? pages.size() / 2 : (pages.size() - 1) / 2);
            }
        }
        return sum;
    }

    private ArrayList<ArrayList<Integer>> SetUpRulesAndGetPages(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        Pattern rulePattern = Pattern.compile("(?<lt>\\d*)\\|(?<gt>\\d*)");
        String line;
        while (scanner.hasNext() && !(line = scanner.nextLine()).isEmpty()) {
            Matcher matcher = rulePattern.matcher(line);

            if (matcher.find()) {
                int key = Integer.parseInt(matcher.group("lt"));
                int value = Integer.parseInt(matcher.group("gt"));
                if (rules.containsKey(key)) {
                    rules.get(key).add(value);
                } else {
                    Set<Integer> lts = new HashSet<>();
                    lts.add(value);
                    rules.put(key, lts);
                }
            } else {
                throw new RuntimeException("Invalid rule: " + line);
            }
        }

        ArrayList<ArrayList<Integer>> pages = new ArrayList<>();

        while (scanner.hasNext()) {
            ArrayList<Integer> page = new ArrayList<>();
            String[] values = scanner.nextLine().split(",");
            for (String value : values) {
                page.add(Integer.parseInt(value));
            }
            pages.add(page);
        }

        return pages;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
