package year.year2023.day15;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Day15 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        String[] steps = new Scanner(file).nextLine().split(",");
        int sum = 0;

        for (String step : steps) {
            int value =  decode(step);
            sum += value;
        }

        return sum;
    }

    private static int decode(String string) {
        int value = 0;
        for (int code : string.toCharArray()) {
            value += code;
            value *= 17;
            value %= 256;
        }
        return value;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Box[] boxes = new Box[256];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
        }

        String[] steps = new Scanner(file).nextLine().split(",");
        for (String step : steps) {
            String[] split = step.split("[=\\-]");
            String label = split[0];
            Box box = boxes[decode(label)];

            if (split.length == 2) {
                int length = Integer.parseInt(split[1]);
                Lens lens = new Lens(length, label);
                if (box.lenses.contains(lens)) {
                    box.lenses.set(box.lenses.indexOf(lens), lens);
                } else {
                    box.lenses.add(lens);
                }
            } else {
                box.lenses.remove(new Lens(-1, label));
            }
        }

        int sum = 0;

        for (int i = 0; i < boxes.length; i++) {
            ArrayList<Lens> lenses = boxes[i].lenses;

            for (int j = 0; j < lenses.size(); j++) {
                Lens lens = lenses.get(j);
                sum += (i + 1) * (j + 1) * lens.length;
            }
        }

        return sum;
    }

    private static class Lens {
        final int length;
        final String label;

        public Lens(int length, String label) {
            this.length = length;
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lens lens = (Lens) o;
            return Objects.equals(label, lens.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }

    private static class Box {
        final ArrayList<Lens> lenses = new ArrayList<>();
    }
}
