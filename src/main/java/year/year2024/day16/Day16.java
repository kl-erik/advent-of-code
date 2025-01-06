package year.year2024.day16;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
        Result minResult = bfs(map).minResult;
        return minResult.turns * 1000 + minResult.steps;
    }

    private Solution bfs(char[][] map) {
        Result[][][] dp = new Result[Direction.values().length][map.length][map[0].length];
        for (Result[][] dir : dp) {
            for (Result[] cell : dir) {
                Arrays.fill(cell, new Result(Integer.MAX_VALUE, Integer.MAX_VALUE));
            }
        }

        Queue<Tile> queue = new LinkedList<>();
        Point startPoint = getEndPoint(map);
        for (Direction startDir : Direction.values()) {
            Point nextPoint = startPoint.add(startDir.v);
            if (map[nextPoint.y][nextPoint.x] != '#') {
                Tile tile = new Tile(startPoint, startDir, new Result(0, 0), null);
                queue.offer(tile);
            }
        }

        Result minResult = new Result(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Set<Tile> minTiles = new HashSet<>();

        while (!queue.isEmpty()) {
            Tile currentTile = queue.poll();
            Point point = currentTile.point;
            Direction dir = currentTile.dir;
            Result result = currentTile.result;

            if (map[point.y][point.x] == 'S') {
                if (dir != Direction.RIGHT) {
                    result.turns++;
                }

                if (result.compareTo(minResult) < 0) {
                    minResult = result;
                    minTiles.clear();
                    minTiles.add(currentTile);
                } else if (result.equals(minResult)) {
                    minTiles.add(currentTile);
                }

                continue;
            }

            if (dp[dir.ordinal()][point.y][point.x].compareTo(result) < 0) {
                continue;
            } else {
                dp[dir.ordinal()][point.y][point.x] = result;
            }

            for (Direction nextDir : Direction.values()) {
                Point nextPoint = point.add(nextDir.v);
                if (map[nextPoint.y][nextPoint.x] != '#') {
                    Result nextResult = new Result(result.turns + (dir == nextDir ? 0 : 1), result.steps + 1);
                    queue.offer(new Tile(nextPoint, nextDir, nextResult, currentTile));
                }
            }
        }

        Set<Point> path = new HashSet<>();
        for (Tile tile : minTiles) {
            path.add(tile.point);
            while (tile.prev != null) {
                path.add(tile.prev.point);
                tile = tile.prev;
            }
        }

        return new Solution(minResult, path);
    }

    private Point getEndPoint(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'E') {
                    return new Point(j, i);
                }
            }
        }

        throw new RuntimeException("End point not found");
    }

    private static class Tile {
        Point point;
        Direction dir;
        Result result;
        Tile prev;

        public Tile(Point point, Direction dir, Result result, Tile prev) {
            this.point = point;
            this.dir = dir;
            this.result = result;
            this.prev = prev;
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
        char[][] map = Utils.toChars(file);
        Set<Point> path = bfs(map).path;
        return path.size();
    }

    private static class Solution {
        private final Result minResult;
        private final Set<Point> path;

        public Solution(Result minResult, Set<Point> path) {
            this.minResult = minResult;
            this.path = path;
        }
    }
}
