package days.day2;

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

    public int getScore() {
        return score;
    }
}
