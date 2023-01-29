package days.day22;

public abstract class Cube extends Board {
    protected final Character[][][] sides;
    protected final int sideLength;
    protected int currentSide;

    public Cube(Character[][][] sides) {
        super();
        this.sides = sides;
        currentSide = 0;
        Character[][] side = sides[currentSide];
        sideLength = side.length;
        setStartPosition(side);
    }

    private void setStartPosition(Character[][] side) {
        for (int i = 0; i < side.length; i++) {
            for (int j = 0; j < side[i].length; j++) {
                if (side[i][j] != '#') {
                    x = j;
                    y = i;
                    return;
                }
            }
        }
    }

    @Override
    public void move(int steps) {
        int previousSide;
        int previousX;
        int previousY;
        Direction previousDirection;

        for (int step = 0; step < steps; step++) {
            previousSide = currentSide;
            previousX = x;
            previousY = y;
            previousDirection = direction;
            step();

            if (x < 0) {
                rotateLeft();
            } else if (x >= sides[currentSide].length) {
                rotateRight();
            } else if (y < 0) {
                rotateUP();
            } else if (y >= sides[currentSide].length) {
                rotateDOWN();
            }

            if (sides[currentSide][y][x] == '#') {
                currentSide = previousSide;
                x = previousX;
                y = previousY;
                direction = previousDirection;
                break;
            }
        }
    }

    protected abstract void rotateUP();

    protected abstract void rotateDOWN();

    protected abstract void rotateRight();

    protected abstract void rotateLeft();
}
