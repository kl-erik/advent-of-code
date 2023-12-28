package year.year2023.day07;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class HandP2 implements Comparable<HandP2> {
    private final Strength[] cards;
    private final int bid;
    private final Type type;

    public HandP2(char[] cardChars, int bid) {
        this.cards = toStrength(cardChars);
        this.bid = bid;
        type = calcType(cardChars);
    }

    private Strength[] toStrength(char[] cardChars) {
        Strength[] cards = new Strength[cardChars.length];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = Strength.valueOf(cardChars[i]);
        }
        return cards;
    }

    private Type calcType(char[] cards) {
        Set<Character> uniqueCards = new HashSet<>();
        for (char card : cards) {
            uniqueCards.add(card);
        }

        int mostOfSame = 0;
        char mostOfSameChar = 0;
        for (char uniqueCard : uniqueCards) {
            int same = 0;
            for (char card : cards) {
                if (card == uniqueCard || card == 'J') {
                    same++;
                }
            }
            if (same > mostOfSame) {
                mostOfSame = same;
                mostOfSameChar = uniqueCard;
            }
        }

        if (mostOfSameChar != 'J') {
            uniqueCards.remove('J');
        }

        switch (uniqueCards.size()) {
            case 1:
                return Type.FIVE;
            case 2:
                return mostOfSame == 4 ? Type.FOUR : Type.HOUSE;
            case 3:
                return mostOfSame == 3 ? Type.THREE : Type.TWO;
            case 4:
                return Type.ONE;
            default:
                return Type.HIGH;
        }
    }

    public int getBid() {
        return bid;
    }

    @Override
    public int compareTo(HandP2 other) {
        int typeComparison = type.compareTo(other.type);

        if (typeComparison == 0) {
            for (int i = 0; i < cards.length; i++) {
                int strengthComparison = cards[i].compareTo(other.cards[i]);

                if (strengthComparison != 0) {
                    return strengthComparison;
                }
            }

            return 0;
        } else {
            return typeComparison;
        }
    }

    private enum Type {
        FIVE, FOUR, HOUSE, THREE, TWO, ONE, HIGH
    }

    private enum Strength {
        A, K, Q, T, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, J;

        public static Strength valueOf(char cardChar) {
            switch (cardChar) {
                case 'A':
                    return A;
                case 'K':
                    return K;
                case 'Q':
                    return Q;
                case 'J':
                    return J;
                case 'T':
                    return T;
                case '9':
                    return NINE;
                case '8':
                    return EIGHT;
                case '7':
                    return SEVEN;
                case '6':
                    return SIX;
                case '5':
                    return FIVE;
                case '4':
                    return FOUR;
                case '3':
                    return THREE;
                case '2':
                    return TWO;
                default:
                    throw new NoSuchElementException();
            }
        }
    }
}
