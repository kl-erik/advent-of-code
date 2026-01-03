package year.year2025.day11;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
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
        Map<String, Set<String>> nodeMaps = parse(file);
        long paths1 = dfs(nodeMaps, "svr", "fft", getAllNodesFrom("fft", nodeMaps), new HashMap<>());
        long paths2 = dfs(nodeMaps, "fft", "dac", getAllNodesFrom("dac", nodeMaps), new HashMap<>());
        long paths3 = dfs(nodeMaps, "dac", "out", new HashSet<>(), new HashMap<>());
        return paths1 * paths2 * paths3;
    }

    private HashSet<String> getAllNodesFrom(String current, Map<String, Set<String>> nodeMaps) {
        HashSet<String> allNodes = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(current);
        while (!stack.isEmpty()) {
            String node = stack.pop();
            allNodes.add(node);
            for (String neighbor : nodeMaps.get(node)) {
                if (!allNodes.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        allNodes.remove(current);
        return allNodes;
    }

    private long dfs(Map<String, Set<String>> nodeMaps, String current, String target, HashSet<String> visited, Map<String, Long> memo) {
        if (current.equals(target)) {
            return 1;
        }

        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        visited.add(current);

        long paths = 0;
        for (String neighbor : nodeMaps.get(current)) {
            if (!visited.contains(neighbor)) {
                paths += dfs(nodeMaps, neighbor, target, visited, memo);
            }
        }

        visited.remove(current);

        memo.put(current, paths);

        return paths;
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
        nodeMaps.put("out", new HashSet<>());
        return nodeMaps;
    }
}
