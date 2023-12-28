package year.year2022.day02;

public enum Outcome {
    LOSS(0),
    DRAW(3),
    WIN(6);

    private final int score;

    Outcome(int score) {
        this.score = score;
    }

    public static Outcome fromChar(char c) {
        switch (c) {
            case 'X':
                return LOSS;
            case 'Y':
                return DRAW;
            case 'Z':
                return WIN;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    public Hand toHand(Hand otherHand) {
        for (Hand hand : Hand.values()) {
            if (hand.compare(otherHand) == this) {
                return hand;
            }
        }

        throw new IllegalStateException("Unexpected value: " + otherHand);
    }

    public int getScore() {
        return score;
    }
}
