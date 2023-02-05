package days.day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static Groove parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Elf[]> elves = new ArrayList<>();

        while (scanner.hasNext()) {
            char[] cs = scanner.nextLine().toCharArray();
            Elf[] row = new Elf[cs.length + 20];

            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == '#') {
                    row[i + 10] = new Elf();
                }
            }

            elves.add(row);
        }

        for (int i = 0; i < 10; i++) {
            elves.add(0, new Elf[elves.get(0).length]);
            elves.add(elves.size(), new Elf[elves.get(0).length]);
        }

        return new Groove(elves.toArray(new Elf[0][]));
    }
}
