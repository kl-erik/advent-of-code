package year.year2024.day07;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<String> equations = Utils.getLines(file);
        Pattern equationPattern = Pattern.compile("(?<result>\\d+): (?<start>(\\d+))\\s?+(?<values>(\\d+\\s?+)+)");

        long sum = 0;

        for (String equation : equations) {
            Matcher matcher = equationPattern.matcher(equation);
            if (matcher.find()) {
                long result = Long.parseLong(matcher.group("result"));
                long start = Long.parseLong(matcher.group("start"));
                long[] values = Arrays.stream(matcher.group("values").split(" ")).mapToLong(Long::parseLong).toArray();
                ArrayList<Long> results = addOrMul(0, new ArrayList<>(List.of(start)), values);
                if (results.contains(result)) {
                    sum += result;
                }
            }
        }

        return sum;
    }

    private ArrayList<Long> addOrMul(int i, ArrayList<Long> sums, long[] values) {
        if (i == values.length) {
            return sums;
        }

        ArrayList<Long> newSums = new ArrayList<>();

        for (Long sum : sums) {
            newSums.add(sum + values[i]);
            newSums.add(sum * values[i]);
        }

        return addOrMul(i + 1, newSums, values);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<String> equations = Utils.getLines(file);
        Pattern equationPattern = Pattern.compile("(?<result>\\d+): (?<start>(\\d+))\\s?+(?<values>(\\d+\\s?+)+)");

        long sum = 0;

        for (String equation : equations) {
            Matcher matcher = equationPattern.matcher(equation);
            if (matcher.find()) {
                String result = matcher.group("result");
                String start = matcher.group("start");
                String[] values = matcher.group("values").split(" ");
                ArrayList<String> results = addOrMulOrCat(0, new ArrayList<>(List.of(start)), values);
                if (results.contains(result)) {
                    sum += Long.parseLong(result);
                }
            }
        }

        return sum;
    }

    private ArrayList<String> addOrMulOrCat(int i, ArrayList<String> strings, String[] values) {
        if (i == values.length) {
            return strings;
        }

        ArrayList<String> newStrings = new ArrayList<>();

        for (String string : strings) {
            newStrings.add(String.valueOf(Long.parseLong(string) + Long.parseLong(values[i])));
            newStrings.add(String.valueOf(Long.parseLong(string) * Long.parseLong(values[i])));
            newStrings.add(string + values[i]);
        }

        return addOrMulOrCat(i + 1, newStrings, values);
    }
}
