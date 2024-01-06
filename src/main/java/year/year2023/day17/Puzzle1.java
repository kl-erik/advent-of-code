package year.year2023.day17;

public class Puzzle1 extends Day17 {
    protected static void goRight(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        sum += map[i][j];

        visited[i][j] = true;

        if (j < solutions.length - 1 && !visited[i][j+1] && steps < 3) {
            goRight(i, j+1, steps+1, sum, fails);
        }

        if (i < solutions.length - 1 && !visited[i+1][j]) {
            goDown(i+1, j, 1, sum, fails);
        }

        if (i > 0 && !visited[i-1][j]) {
            goUp(i-1, j, 1, sum, fails);
        }

        visited[i][j] = false;
    }

    private static void goLeft(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        sum += map[i][j];

        visited[i][j] = true;

        if (j > 0 && !visited[i][j-1] && steps < 3) {
            goLeft(i, j-1, steps+1, sum, fails);
        }

        if (i < solutions.length - 1 && !visited[i+1][j]) {
            goDown(i+1, j, 1, sum, fails);
        }

        if (i > 0 && !visited[i-1][j]) {
            goUp(i-1, j, 1, sum, fails);
        }

        visited[i][j] = false;
    }

    protected static void goDown(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        sum += map[i][j];

        visited[i][j] = true;


        if (i < solutions.length - 1 && !visited[i+1][j] && steps < 3) {
            goDown(i+1, j, steps+1, sum, fails);
        }

        if (j < solutions.length - 1 && !visited[i][j+1]) {
            goRight(i, j+1, 1, sum, fails);
        }

        if (j > 0 && !visited[i][j-1]) {
            goLeft(i, j-1, 1, sum, fails);
        }

        visited[i][j] = false;
    }

    private static void goUp(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        sum += map[i][j];

        visited[i][j] = true;

        if (i > 0 && !visited[i-1][j] && steps < 3) {
            goUp(i-1, j, steps+1, sum, fails);
        }

        if (j < solutions.length - 1 && !visited[i][j+1]) {
            goRight(i, j+1, 1, sum, fails);
        }

        if (j > 0 && !visited[i][j-1]) {
            goLeft(i, j-1, 1, sum, fails);
        }

        visited[i][j] = false;
    }
}
