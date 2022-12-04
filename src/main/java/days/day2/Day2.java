package days.day2;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 implements Day {
    public int puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int totalScore = 0;

        while (scanner.hasNextLine()) {
            String round = scanner.nextLine();
            Hand otherHand = Hand.fromChar(round.charAt(0));
            Hand myHand = Hand.fromChar(round.charAt(2));
            Outcome outcome = myHand.compare(otherHand);
            totalScore += outcome.getScore() + myHand.getScore();
        }

        return totalScore;
    }

    @Override
    public int puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int totalScore = 0;

        while (scanner.hasNextLine()) {
            String round = scanner.nextLine();
            Hand otherHand = Hand.fromChar(round.charAt(0));
            Outcome outcome = Outcome.fromChar(round.charAt(2));
            Hand myHand = outcome.toHand(otherHand);
            totalScore += outcome.getScore() + myHand.getScore();
        }

        return totalScore;
    }
}
