package year.year2025.day11;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

public class Day11 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Map<String, Set<String>> nodeMaps = parse(file);

        Stack<String> stack = new Stack<>();
        stack.push("you");
        int paths = 0;
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals("out")) {
                paths++;
                continue;
            }
            for (String neighbor : nodeMaps.get(current)) {
                stack.push(neighbor);
            }
        }
        return paths;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }

    private static Map<String, Set<String>> parse(File file) throws FileNotFoundException {
        Map<String, Set<String>> nodeMaps = new HashMap<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            Pattern pattern = Pattern.compile("(?<node>\\w+):(?<connections>(\\s\\w+)+)");
            String line = sc.nextLine();
            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                String node = matcher.group("node");
                String[] connections = matcher.group("connections").substring(1).split(" ");
                nodeMaps.put(node, Set.of(connections));
            }
        }
        return nodeMaps;
    }
}
