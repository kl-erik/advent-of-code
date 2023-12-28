package year.year2022.day06;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Day6 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        return solve(file, 4);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return solve(file, 14);
    }

    private static Integer solve(File file, int size) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        char[] line = scanner.nextLine().toCharArray();
        Queue<Character> visited = new LinkedList<>();

        for (int i = 0; i < line.length; i++) {
            if (visited.contains(line[i]))
                while (visited.remove() != line[i]);
            visited.add(line[i]);
            if (visited.size() == size)
                return i + 1;
        }

        throw new NoSuchElementException();
    }
}
