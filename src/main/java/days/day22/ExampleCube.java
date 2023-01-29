package days.day22;

import static days.day22.Direction.*;

public class ExampleCube extends Cube {
    public ExampleCube(Character[][][] sides) {
        super(sides);
    }

    @Override
    protected void rotateUP() {
        if (currentSide == 0) {
            setValues(1, x, y, DOWN);
        } else if (currentSide == 1) {
            setValues(0, x, y, DOWN);
        } else if (currentSide == 2) {
            setValues(0, 0, x, RIGHT);
        } else if (currentSide == 3) {
            setValues(0, x, sideLength - 1, direction);
        } else if (currentSide == 4) {
            setValues(3, x, sideLength - 1, direction);
        } else if (currentSide == 5) {
            setValues(3, sideLength - 1, sideLength - 1 - x, LEFT);
        }
    }

    @Override
    protected void rotateDOWN() {
        if (currentSide == 0) {
            setValues(3, x, 0, direction);
        } else if (currentSide == 1) {
            setValues(4, x, y, UP);
        } else if (currentSide == 2) {
            setValues(4, 0, sideLength - 1 - x, RIGHT);
        } else if (currentSide == 3) {
            setValues(4, x, 0, direction);
        } else if (currentSide == 4) {
            setValues(1, sideLength - 1 - x, sideLength - 1, UP);
        } else if (currentSide == 5) {
            setValues(currentSide, 0, sideLength - 1 - x, RIGHT);
        }
    }

    @Override
    protected void rotateRight() {
        if (currentSide == 0) {
            setValues(5, sideLength - 1, sideLength - 1 - y, LEFT);
        } else if (currentSide == 1 || currentSide == 2 || currentSide == 4) {
            setValues(currentSide + 1, 0, y, direction);
        } else if (currentSide == 3) {
            setValues(5, sideLength - 1 - y, 0, DOWN);
        } else if (currentSide == 5) {
            setValues(0, sideLength - 1, sideLength - 1 - y, LEFT);
        }
    }

    @Override
    protected void rotateLeft() {
        if (currentSide == 0) {
            setValues(2, y, 0, DOWN);
        } else if (currentSide == 1) {
            setValues(5, sideLength - 1 - y, sideLength - 1, UP);
        } else if (currentSide == 2 || currentSide == 3 || currentSide == 5) {
            setValues(currentSide - 1, sideLength - 1, y, direction);
        } else if (currentSide == 4) {
            setValues(2, sideLength - 1 - y, sideLength - 1, UP);
        }
    }

    private void setValues(int currentSide, int x, int y, Direction direction) {
        this.currentSide = currentSide;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public int getRow() {
        if (currentSide == 0) {
            return y + 1;
        } else if (currentSide == 1 || currentSide == 2 || currentSide == 3) {
            return y + 1 + sideLength;
        } else if (currentSide == 4 || currentSide == 5) {
            return y + 1 + 2 * sideLength;
        }

        throw new RuntimeException();
    }

    @Override
    public int getColumn() {
        if (currentSide == 0 || currentSide == 3 || currentSide == 4) {
            return x + 1 + 2 * sideLength;
        } else if (currentSide == 1) {
            return x + 1;
        } else if (currentSide == 2) {
            return x + 1 + sideLength;
        } else if (currentSide == 5) {
            return x + 1 + 3 * sideLength;
        }

        throw new RuntimeException();
    }
}
