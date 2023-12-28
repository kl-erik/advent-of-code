package year.year2022.day02;

public enum Hand {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    Hand(int score) {
        this.score = score;
    }

    public static Hand fromChar(char c) {
        switch (c) {
            case 'A':
            case 'X':
                return ROCK;
            case 'B':
            case 'Y':
                return PAPER;
            case 'C':
            case 'Z':
                return SCISSORS;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    public Outcome compare(Hand other) {
        if (this == other) {
            return Outcome.DRAW;
        }

        switch (this) {
            case ROCK:
                switch (other) {
                    case PAPER:
                        return Outcome.LOSS;
                    case SCISSORS:
                        return Outcome.WIN;
                }
            case PAPER:
                switch (other) {
                    case ROCK:
                        return Outcome.WIN;
                    case SCISSORS:
                        return Outcome.LOSS;
                }
            case SCISSORS:
                switch (other) {
                    case ROCK:
                        return Outcome.LOSS;
                    case PAPER:
                        return Outcome.WIN;
                }
            default:
                throw new IllegalStateException("Unexpected value: " + other);
        }
    }

    public int getScore() {
        return score;
    }
}
