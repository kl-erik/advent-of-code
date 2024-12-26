package year.year2024.day13;

import year.Point;

public class Machine {
    private final Point a, b, prize;

    public Machine(Point a, Point b, Point prize) {
        this.a = a;
        this.b = b;
        this.prize = prize;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getPrize() {
        return prize;
    }
}
