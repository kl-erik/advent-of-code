package days.day22;

import static days.day22.Direction.*;

public class Player {
    private int x;
    private int y;
    private Direction direction;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = RIGHT;
    }

    public void move() {
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
}
