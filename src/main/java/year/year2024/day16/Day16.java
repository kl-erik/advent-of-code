package year.year2024.day16;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Day16 implements Day {
    enum Direction {
        UP(new Point(0, -1)), RIGHT(new Point(1, 0)), DOWN(new Point(0, 1)), LEFT(new Point(-1, 0));

        private final Point v;

        Direction(Point v) {
            this.v = v;
        }
    }

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);
        Result minResult = bfs(map);
        return minResult.turns * 1000 + minResult.steps;
    }

    private Result bfs(char[][] map) {
        Result[][][] dp = new Result[Direction.values().length][map.length][map[0].length];
        for (Result[][] dir : dp) {
            for (Result[] cell : dir) {
                Arrays.fill(cell, new Result(Integer.MAX_VALUE, Integer.MAX_VALUE));
            }
        }

        Queue<Tile> queue = new LinkedList<>();
        Point startPoint = getPoint(map, 'E');

        for (Direction startDir : Direction.values()) {
            Point nextPoint = startPoint.add(startDir.v);
            if (map[nextPoint.y][nextPoint.x] != '#') {
                Tile tile = new Tile(startPoint, startDir, new Result(0, 0));
                queue.offer(tile);
            }
        }

        Result minResult = new Result(Integer.MAX_VALUE, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            Tile currentTile = queue.poll();
            Point point = currentTile.point;
            Direction dir = currentTile.dir;
            Result result = currentTile.result;

            if (map[point.y][point.x] == 'S') {
                if (dir != Direction.RIGHT) {
                    result.turns++;
                }

                minResult = minResult.compareTo(result) <= 0 ? minResult : result;
                continue;
            }

            if (dp[dir.ordinal()][point.y][point.x].compareTo(result) <= 0) {
                continue;
            } else {
                dp[dir.ordinal()][point.y][point.x] = result;
            }

            for (Direction nextDir : Direction.values()) {
                Point nextPoint = point.add(nextDir.v);
                if (map[nextPoint.y][nextPoint.x] != '#') {
                    Result nextResult = new Result(result.turns + (dir == nextDir ? 0 : 1), result.steps + 1);
                    queue.offer(new Tile(nextPoint, nextDir, nextResult));
                }
            }
        }

        return minResult;
    }

    private Point getPoint(char[][] map, char s) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == s) {
                    return new Point(j, i);
                }
            }
        }

        throw new RuntimeException("Point not found: " + s);
    }

    private static class Tile {
        Point point;
        Direction dir;
        Result result;

        public Tile(Point point, Direction dir, Result result) {
            this.point = point;
            this.dir = dir;
            this.result = result;
        }
    }

    static class Result implements Comparable<Result> {
        int turns;
        int steps;

        public Result(int turns, int steps) {
            this.turns = turns;
            this.steps = steps;
        }

        public Result add(Result result) {
            turns += result.turns;
            steps += result.steps;
            return this;
        }

        @Override
        public int compareTo(Result result) {
            if (turns == result.turns) {
                return steps - result.steps;
            }
            return turns - result.turns;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Result)) return false;
            Result result = (Result) o;
            return turns == result.turns && steps == result.steps;
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
