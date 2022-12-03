package days.day2;

public enum Outcome {
    LOSS(0),
    DRAW(3),
    WIN(6);

    private final int score;

    Outcome(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
