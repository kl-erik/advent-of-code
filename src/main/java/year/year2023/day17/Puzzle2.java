package year.year2023.day17;

public class Puzzle2 extends Day17 {
    protected static void goRight(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (steps < 4) {
            if (j < solutions[i].length - 1) {
                visited[i][j] = true;
                goRight(i, j + 1, steps + 1, sum + map[i][j + 1], fails);
                visited[i][j] = false;
            }
        } else {
            if (solutions[i][j] > sum) {
                solutions[i][j] = sum;
                fails = 0;
            } else {
                fails++;
            }

            if (fails > 5) {
                return;
            }

            if (j < solutions[i].length - 1 && !visited[i][j + 1] && steps < 10) {
                goRight(i, j + 1, steps + 1, sum + map[i][j + 1], fails);
            }

            if (i < solutions.length - 1 && !visited[i + 1][j]) {
                goDown(i + 1, j, 1, sum + map[i + 1][j], fails);
            }

            if (i > 0 && !visited[i - 1][j]) {
                goUp(i - 1, j, 1, sum + map[i - 1][j], fails);
            }

            visited[i][j] = false;
        }
    }

    private static void goLeft(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (steps < 4) {
            if (j > 0) {
                visited[i][j] = true;
                goLeft(i, j - 1, steps + 1, sum + map[i][j - 1], fails);
                visited[i][j] = false;
            }
        } else {
            if (solutions[i][j] > sum) {
                solutions[i][j] = sum;
                fails = 0;
            } else {
                fails++;
            }

            if (fails > 5) {
                return;
            }

            visited[i][j] = true;

            if (j > 0 && !visited[i][j - 1] && steps < 10) {
                goLeft(i, j - 1, steps + 1, sum + map[i][j - 1], fails);
            }

            if (i < solutions.length - 1 && !visited[i + 1][j]) {
                goDown(i + 1, j, 1, sum + map[i + 1][j], fails);
            }

            if (i > 0 && !visited[i - 1][j]) {
                goUp(i - 1, j, 1, sum + map[i - 1][j], fails);
            }

            visited[i][j] = false;
        }
    }

    protected static void goDown(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (steps < 4) {
            if (i < solutions.length - 1){
                visited[i][j] = true;
                goDown(i + 1, j, steps + 1, sum + map[i + 1][j], fails);
                visited[i][j] = false;
            }
        } else {
            if (solutions[i][j] > sum) {
                solutions[i][j] = sum;
                fails = 0;
            } else {
                fails++;
            }

            if (fails > 5) {
                return;
            }

            visited[i][j] = true;


            if (i < solutions.length - 1 && !visited[i + 1][j] && steps < 10) {
                goDown(i + 1, j, steps + 1, sum + map[i + 1][j], fails);
            }

            if (j < solutions[i].length - 1 && !visited[i][j + 1]) {
                goRight(i, j + 1, 1, sum + map[i][j + 1], fails);
            }

            if (j > 0 && !visited[i][j - 1]) {
                goLeft(i, j - 1, 1, sum + map[i][j - 1], fails);
            }

            visited[i][j] = false;
        }
    }

    private static void goUp(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (steps < 4) {
            if (i > 0) {
                visited[i][j] = true;
                goUp(i - 1, j, steps + 1, sum + map[i - 1][j], fails);
                visited[i][j] = false;
            }
        } else {
            if (solutions[i][j] > sum) {
                solutions[i][j] = sum;
                fails = 0;
            } else {
                fails++;
            }

            if (fails > 5) {
                return;
            }

            visited[i][j] = true;

            if (i > 0 && !visited[i - 1][j] && steps < 10) {
                goUp(i - 1, j, steps + 1, sum + map[i - 1][j], fails);
            }

            if (j < solutions[i].length - 1 && !visited[i][j + 1]) {
                goRight(i, j + 1, 1, sum + map[i][j + 1], fails);
            }

            if (j > 0 && !visited[i][j - 1]) {
                goLeft(i, j - 1, 1, sum + map[i][j - 1], fails);
            }

            visited[i][j] = false;
        }
    }
}
