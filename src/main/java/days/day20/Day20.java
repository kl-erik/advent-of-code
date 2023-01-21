package days.day20;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Day20 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Number[] numbers = parse(file);
        int i;

        for (int j = 0; j < numbers.length; j++) {
            i = findValueIndex(numbers, j);
            move(numbers, i);
        }

        return getGroveSum(numbers);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Number[] numbers = parse(file);
        for (Number number : numbers) {
            number.v *= 811589153;
        }

        int i;

        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < numbers.length; j++) {
                i = findValueIndex(numbers, j);
                move(numbers, i);
            }
        }

        return getGroveSum(numbers);
    }

    private void move(Number[] numbers, int i) {
        if (numbers[i].v == 0)
            return;

        long j = numbers[i].v + i;

        if (j >= numbers.length) {
            j--;
            j %= numbers.length - 1;
            j++;
            if (j == numbers.length - 1)
                j = 0;
        } else if (j <= 0) {
            j %= numbers.length - 1;
            j += numbers.length;
            j--;
        }

        Number tmp = numbers[i];

        if (j < i) {
            while (i > j) {
                numbers[i] = numbers[i-1];
                i--;
            }
        } else {
            while (i < j) {
                numbers[i] = numbers[i+1];
                i++;
            }
        }

        numbers[i] = tmp;
    }

    private static int findValueIndex(Number[] numbers, int j) {
        for (int i = 0; i < numbers.length; i++)
            if (numbers[i].i == j) return i;
        throw new NoSuchElementException();
    }

    private Object getGroveSum(Number[] numbers) {
        int i;
        i = findZeroIndex(numbers);
        long v1 = numbers[(i + 1000) % numbers.length].v;
        long v2 = numbers[(i + 2000) % numbers.length].v;
        long v3 = numbers[(i + 3000) % numbers.length].v;

        return v1 + v2 + v3;
    }

    private static int findZeroIndex(Number[] numbers) {
        for (int j = 0; j < numbers.length; j++) {
            if (numbers[j].v == 0) {
                return j;
            }
        }

        throw new NoSuchElementException();
    }

    private static Number[] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Number> numbers = new ArrayList<>();
        int i = 0;

        while (scanner.hasNextLine()) {
            numbers.add(new Number(i, Integer.parseInt(scanner.nextLine())));
            i++;
        }

        return toArray(numbers);
    }

    private static Number[] toArray(ArrayList<Number> list) {
        Number[] array = new Number[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i);
        return array;
    }

    static class Number {
        int i;
        long v;

        public Number(int i, int v) {
            this.i = i;
            this.v = v;
        }
    }
}
