package days.day16;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 implements Day {
    HashMap<Cave, HashMap<Cave, Integer>> distances;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        HashMap<String, Cave> caves = parse(file);
        distances = getDistances(new HashSet<>(caves.values()));
        Cave start = caves.get("AA");
        int[] sums = new int[caves.size()];
        int i = 0;

        HashMap<String, Cave> skip = new HashMap<>();
        for (String name : caves.keySet()) {
            Cave cave = caves.get(name);
            if (!cave.name.equals("AA") && cave.flowRate == 0) {
                skip.put(name, cave);
            }
        }

        caves.keySet().removeAll(skip.keySet());

        for (Cave cave : caves.values()) {
            int distance = distances.get(start).get(cave);
            sums[i] = bfs(cave, new HashSet<>(caves.values()), distance, 30);
            i++;
        }

        int maxSum = 0;
        for (int sum : sums) {
            if (sum > maxSum) maxSum = sum;
        }

        return maxSum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        HashMap<String, Cave> caves = parse(file);
        distances = getDistances(new HashSet<>(caves.values()));
        Cave start = caves.get("AA");
        int maxSum = 0;

        HashMap<String, Cave> skip = new HashMap<>();
        for (String name : caves.keySet()) {
            Cave cave = caves.get(name);
            if (!cave.name.equals("AA") && cave.flowRate == 0) {
                skip.put(name, cave);
            }
        }

        caves.keySet().removeAll(skip.keySet());
        ArrayList<Cave> caveList = new ArrayList<>(caves.values());

        for (int i = 0; i < caveList.size(); i++) {
            Cave cave1 = caveList.get(i);

            for (int j = i; j < caveList.size(); j++) {
                Cave cave2 = caveList.get(j);

                if (cave1 != cave2) {
                    int sum = bfs(cave1, cave2, new HashSet<>(caves.values()), distances.get(start).get(cave1), distances.get(start).get(cave2));
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
        }

        return maxSum;
    }

    private int bfs(Cave start1, Cave start2, HashSet<Cave> toVisit, int i, int j) {
        if (toVisit.isEmpty()) return 0;

        toVisit.remove(start1);
        toVisit.remove(start2);
        i++;
        j++;
        int maxDistance1 = 0;
        int maxDistance2 = 0;
        int maxFlow1 = 0;
        int maxFlow2 = 0;

        int maxSum = 0;
        for (Cave next1 : toVisit) {
            int distance1 = i + distances.get(start1).get(next1);
            if (next1.flowRate < maxFlow1 && distance1 > maxDistance1) continue;

            for (Cave next2 : toVisit) {
                if (next1 != next2) {
                    int distance2 = j + distances.get(start2).get(next2);
                    if (next2.flowRate < maxFlow2 && distance2 > maxDistance2) continue;
                    int sum = 0;

                    if (distance1 <= 26 && distance2 <= 26) {
                        sum = bfs(next1, next2, new HashSet<>(toVisit), distance1, distance2);
                    } else if (distance1 <= 26) {
                        sum = bfs(next1, new HashSet<>(toVisit), distance1, 26);
                    } else if (distance2 <= 26) {
                        sum = bfs(next2, new HashSet<>(toVisit), distance2, 26);
                    }

                    if (sum > maxSum) {
                        maxSum = sum;
                    }

                    maxDistance1 = distance1;
                    maxDistance2 = distance2;
                    maxFlow1 = next1.flowRate;
                    maxFlow2 = next2.flowRate;
                }
            }
        }

        return maxSum + start1.flowRate * (26 - i) + start2.flowRate * (26 - j);
    }

    private int bfs(Cave start, HashSet<Cave> toVisit, int i, int limit) {
        if (toVisit.isEmpty()) return 0;

        toVisit.remove(start);
        i++;
        int maxSum = 0;
        int maxDistance = 0;
        int maxFlow = 0;

        for (Cave next : toVisit) {
            int distance = i + distances.get(start).get(next);

            if (next.flowRate < maxFlow && distance > maxDistance) continue;

            if (distance < limit) {
                int sum = bfs(next, new HashSet<>(toVisit), distance, limit);

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum + start.flowRate * (limit - i);
    }

    private HashMap<Cave, HashMap<Cave, Integer>> getDistances(HashSet<Cave> caves) {
        HashMap<Cave, HashMap<Cave, Integer>> distances = new HashMap<>();

        for (Cave cave : caves) {
            distances.put(cave, new HashMap<>());
        }

        for (Cave cave : caves) {
            HashMap<Cave, Integer> caveDistances = distances.get(cave);
            Set<Cave> visited = new HashSet<>();
            Set<Cave> toVisit = new HashSet<>();
            toVisit.add(cave);
            int distance = 0;

            while (!toVisit.isEmpty()) {
                Set<Cave> visitNext = new HashSet<>();
                for (Cave other : toVisit) {
                    caveDistances.put(other, distance);
                    visited.add(other);
                    visitNext.addAll(other.nextCaves);
                }

                visitNext.removeAll(visited);
                toVisit = visitNext;
                distance++;
            }
        }

        return distances;
    }

    private static HashMap<String, Cave> parse(File file) throws FileNotFoundException {
        HashMap<String, Cave> caves = new HashMap<>();
        HashMap<String, String[]> connections = new HashMap<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("Valve ");
            split = split[1].split(" has flow rate=");
            String caveName = split[0];
            split = split[1].split("; tunnels? leads? to valves? ");
            int flowRate = Integer.parseInt(split[0]);
            String[] nextCaveNames = split[1].split(", ");
            Cave cave = new Cave(caveName, flowRate);
            caves.put(caveName, cave);
            connections.put(caveName, nextCaveNames);
        }

        for (String caveName : caves.keySet()) {
            Cave cave = caves.get(caveName);

            for (String connection : connections.get(caveName)) {
                Cave nextCave = caves.get(connection);
                cave.connect(nextCave);
            }
        }

        return caves;
    }

    private static class Cave {
        String name;
        int flowRate;
        Set<Cave> nextCaves;

        public Cave(String name, int flowRate) {
            this.name = name;
            this.flowRate = flowRate;
            this.nextCaves = new HashSet<>();
        }

        public void connect(Cave nextCave) {
            this.nextCaves.add(nextCave);
            nextCave.nextCaves.add(this);
        }
    }
}
