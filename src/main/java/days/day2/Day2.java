package days.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public int puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int totalScore = 0;

        while (scanner.hasNextLine()) {
            String hands = scanner.nextLine();
            Hand otherHand = Hand.fromChar(hands.charAt(0));
            Hand myHand = Hand.fromChar(hands.charAt(2));
            Outcome outcome = myHand.compare(otherHand);
            totalScore += outcome.getScore() + myHand.getScore();
        }

        return totalScore;
    }
}
