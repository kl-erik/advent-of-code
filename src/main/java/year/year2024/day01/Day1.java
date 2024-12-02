package year.year2024.day01;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static year.Utils.getLines;

public class Day1 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<String> lines = getLines(file);
        ArrayList<Point> pairs = getPairs(lines);
        PriorityQueue<Integer> list1 =
                new PriorityQueue<>(pairs.stream().map(Point::getX).collect(Collectors.toList()));
        PriorityQueue<Integer> list2 =
                new PriorityQueue<>(pairs.stream().map(Point::getY).collect(Collectors.toList()));
        ArrayList<Integer> distances = calculateDistances(list1, list2);
        return distances.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<String> lines = getLines(file);
        ArrayList<Point> pairs = getPairs(lines);
        PriorityQueue<Integer> list1 =
                new PriorityQueue<>(pairs.stream().map(Point::getX).collect(Collectors.toList()));
        PriorityQueue<Integer> list2 =
                new PriorityQueue<>(pairs.stream().map(Point::getY).collect(Collectors.toList()));
        list1.retainAll(list2);
        list2.retainAll(list1);
        HashMap<Integer, Integer> appearancesMap = new HashMap<>();
        return list1.stream().mapToInt(x -> getAppearances(x, list2, appearancesMap)).sum();
    }

    private ArrayList<Point> getPairs(ArrayList<String> lines) {
        Pattern pattern = Pattern.compile("(?<left>\\d+)\\s*(?<right>\\d+)");
        ArrayList<Point> pairs = new ArrayList<>();

        for (String line : lines) {
            var matcher = pattern.matcher(line);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group("left"));
                int y = Integer.parseInt(matcher.group("right"));
                pairs.add(new Point(x, y));
            }
        }

        return pairs;
    }

    private ArrayList<Integer> calculateDistances(PriorityQueue<Integer> list1, PriorityQueue<Integer> list2) {
        ArrayList<Integer> distances = new ArrayList<>();
        while (!list1.isEmpty() && !list2.isEmpty()) {
            int left = list1.poll();
            int right = list2.poll();
            distances.add(Math.abs(left - right));
        }
        return distances;
    }

    private int getAppearances(int x, PriorityQueue<Integer> list, HashMap<Integer, Integer> appearancesMap) {
        if (!appearancesMap.containsKey(x)) {
            int appearances = (int) list.stream().filter(y -> y == x).count();
            appearancesMap.put(x, x * appearances);
        }

        return appearancesMap.get(x);
    }
}
