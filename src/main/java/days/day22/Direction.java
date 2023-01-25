package days.day22;

enum Direction {
    RIGHT(0),
    DOWN(1),
    LEFT(2),
    UP(3);

    private final int val;

    Direction(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
