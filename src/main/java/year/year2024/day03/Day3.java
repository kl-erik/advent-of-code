package year.year2024.day03;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static year.Utils.getLines;

public class Day3 implements Day {
    private static boolean enabled = true;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<String> memory = getLines(file);
        Pattern mulInstructionPattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Pattern mulInstructionPatternGrouped = Pattern.compile("mul\\((?<a>\\d{1,3}),(?<b>\\d{1,3})\\)");
        int sum = 0;

        for (String line : memory) {
            ArrayList<String> instructions = getInstructions(mulInstructionPattern, line);
            sum += instructions.stream().mapToInt(instruction -> executeInstruction(mulInstructionPatternGrouped,
                    instruction)).sum();
        }

        return sum;
    }

    private static ArrayList<String> getInstructions(Pattern instructionPattern, String memory) {
        Matcher matcher = instructionPattern.matcher(memory);
        ArrayList<String> instructions = new ArrayList<>();

        while (matcher.find()) {
            String instruction = matcher.group();

            if (instruction.equals("don't()")) {
                enabled = false;
            } else if (instruction.equals("do()")) {
                enabled = true;
            } else if (enabled) {
                instructions.add(instruction);
            }
        }

        return instructions;
    }

    private Integer executeInstruction(Pattern instructionPattern, String instruction) {
        Matcher matcher = instructionPattern.matcher(instruction);
        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group("a"));
            int b = Integer.parseInt(matcher.group("b"));
            return a * b;
        }

        return 0;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<String> memory = getLines(file);
        Pattern mulInstructionPattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)");
        Pattern mulInstructionPatternGrouped = Pattern.compile("mul\\((?<a>\\d{1,3}),(?<b>\\d{1,3})\\)");
        int sum = 0;

        for (String line : memory) {
            ArrayList<String> instructions = getInstructions(mulInstructionPattern, line);
            sum += instructions.stream().mapToInt(instruction -> executeInstruction(mulInstructionPatternGrouped,
                    instruction)).sum();
        }

        return sum;
    }
}
