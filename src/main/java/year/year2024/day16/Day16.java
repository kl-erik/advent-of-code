package year.year2024.day16;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

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
        return dijkstra(map).minResult;
    }

    private Solution dijkstra(char[][] map) {
        Tile[][][] tiles = new Tile[map.length][map[0].length][Direction.values().length];

        Tile startTile = null;
        Set<Tile> endTiles = new HashSet<>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (map[i][j] != '#') {
                    for (Direction dir : Direction.values()) {
                        Tile tile = new Tile(new Point(j, i), dir, Integer.MAX_VALUE);
                        tiles[i][j][dir.ordinal()] = tile;
                        if (map[i][j] == 'S' && dir == Direction.RIGHT) {
                            tile.dist = 0;
                            startTile = tile;
                        } else if (map[i][j] == 'E') {
                            endTiles.add(tile);
                        }
                    }
                }
            }
        }
        assert startTile != null;
        assert !endTiles.isEmpty();

        PriorityQueue<Tile> toVisit = new PriorityQueue<>();
        toVisit.offer(startTile);

        while (!toVisit.isEmpty()) {
            Tile v = toVisit.poll();

            Set<Tile> neighbours = getNeighbours(v, tiles);

            for (Tile u : neighbours) {
                int newDist = v.dist + (v.dir == u.dir ? 1 : 1001);

                if (newDist <= u.dist) {
                    if (newDist < u.dist) {
                        u.dist = newDist;
                        toVisit.offer(u);
                        u.prev.clear();
                    }

                    u.prev.add(v);
                }
            }
        }

        Tile minEndTile = endTiles.stream().min(Tile::compareTo).orElseThrow();
        Set<Tile> minEndTiles = endTiles.stream().filter(tile -> tile.compareTo(minEndTile) == 0).collect(Collectors.toSet());

        Set<Point> minPaths = new HashSet<>();
        while (true) {
            Set<Tile> prevEndTiles = new HashSet<>();
            for (Tile minTile : minEndTiles) {
                prevEndTiles.addAll(minTile.prev);
                minPaths.add(minTile.point);
            }
            if (prevEndTiles.isEmpty()) {
                break;
            }
            minEndTiles = prevEndTiles;
        }

        return new Solution(minEndTile.dist, minPaths.size());
    }

    private static Set<Tile> getNeighbours(Tile tile, Tile[][][] tiles) {
        Direction opposite = Direction.values()[(tile.dir.ordinal() + 2) % Direction.values().length];

        Set<Tile> neighbours = new HashSet<>();
        for (Direction dir : Direction.values()) {
            if (dir == opposite) {
                continue;
            }
            Point nextPoint = tile.point.add(dir.v);
            if (tiles[nextPoint.y][nextPoint.x][dir.ordinal()] != null) {
                neighbours.add(tiles[nextPoint.y][nextPoint.x][dir.ordinal()]);
            }
        }
        return neighbours;
    }

    private static class Tile implements Comparable<Tile> {
        private final Point point;
        Direction dir;
        int dist;
        Set<Tile> prev = new HashSet<>();

        public Tile(Point point, Direction dir, int dist) {
            this.point = point;
            this.dir = dir;
            this.dist = dist;
        }

        @Override
        public int compareTo(Tile tile) {
            return Integer.compare(dist, tile.dist);
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);
        return dijkstra(map).minPath;
    }

    private static class Solution {
        private final int minResult;
        private final int minPath;

        public Solution(int minResult, int minPath) {
            this.minResult = minResult;
            this.minPath = minPath;
        }
    }
}
