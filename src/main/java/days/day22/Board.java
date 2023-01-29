package days.day22;

import static days.day22.Direction.*;

public abstract class Board {
    protected int x;
    protected int y;
    protected Direction direction;

    public Board() {
        this.direction = RIGHT;
    }

    public abstract void move(int steps);

    public void step() {
        switch (direction) {
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case UP:
                y--;
                break;
        }
    }

    public void turn(String instruction) {
        switch (instruction) {
            case "R":
                turnRight();
                break;
            case "L": turnLeft();
        }
    }

    public void turnRight() {
        switch (direction) {
            case RIGHT:
                direction = DOWN;
                break;
            case DOWN:
                direction = LEFT;
                break;
            case LEFT:
                direction = UP;
                break;
            case UP:
                direction = RIGHT;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case RIGHT:
                direction = UP;
                break;
            case UP:
                direction = LEFT;
                break;
            case LEFT:
                direction = DOWN;
                break;
            case DOWN:
                direction = RIGHT;
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public abstract int getRow();

    public abstract int getColumn();
}
