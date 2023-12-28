package year.year2023.day07;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<HandP1> hands = parseP1(file);
        Collections.sort(hands);
        Collections.reverse(hands);
        return countWinningsP1(hands);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<HandP2> hands = parseP2(file);
        Collections.sort(hands);
        Collections.reverse(hands);
        return countWinningsP2(hands);
    }

    private static int countWinningsP1(ArrayList<HandP1> hands) {
        int winnings = 0;
        for (int i = 0; i < hands.size(); i++) {
            winnings += hands.get(i).getBid() * (i + 1);
        }
        return winnings;
    }

    private static int countWinningsP2(ArrayList<HandP2> hands) {
        int winnings = 0;
        for (int i = 0; i < hands.size(); i++) {
            winnings += hands.get(i).getBid() * (i + 1);
        }
        return winnings;
    }

    private ArrayList<HandP1> parseP1(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<HandP1> hands = new ArrayList<>();

            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(" ");
                char[] cards = split[0].toCharArray();
                int bid = Integer.parseInt(split[1]);
                hands.add(new HandP1(cards, bid));
            }

            return hands;
        }
    }

    private ArrayList<HandP2> parseP2(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<HandP2> hands = new ArrayList<>();

            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(" ");
                char[] cards = split[0].toCharArray();
                int bid = Integer.parseInt(split[1]);
                hands.add(new HandP2(cards, bid));
            }

            return hands;
        }
    }
}
