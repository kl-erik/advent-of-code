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
        ArrayList<Cave> caveList = new ArrayList<>(caves.values());
        int maxSum = 0;

        for (int i = 0; i < caveList.size(); i++) {
            Cave cave1 = caveList.get(i);

            if (cave1.flowRate != 0) {
                for (int j = i; j < caveList.size(); j++) {
                    Cave cave2 = caveList.get(j);

                    if (cave2.flowRate != 0) {
                        if (cave1 != cave2) {
                            int sum = bfs(cave1, cave2, new HashSet<>(caves.values()), distances.get(start).get(cave1), distances.get(start).get(cave2));
                            if (sum > maxSum) {
                                maxSum = sum;
                            }
                        }
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

        int maxSum = 0;
        for (Cave next1 : toVisit) {
            if (next1.flowRate != 0 && i + distances.get(start1).get(next1) < 26) {
                for (Cave next2 : toVisit) {
                    if (next2.flowRate != 0 && j + distances.get(start2).get(next2) < 26) {
                        if (next1 != next2) {
                            int sum = bfs(next1, next2, new HashSet<>(toVisit), i + distances.get(start1).get(next1), j + distances.get(start2).get(next2));

                            if (sum > maxSum) {
                                maxSum = sum;
                            }
                        }
                    } else if (distances.get(start2).get(next2) > 26) {
                        int sum = bfs(next1, new HashSet<>(toVisit), i + distances.get(start1).get(next1), 26);

                        if (sum > maxSum) {
                            maxSum = sum;
                        }
                    }
                }
            } else if (i + distances.get(start1).get(next1) > 26) {
                for (Cave next2 : toVisit) {
                    if (next2.flowRate != 0 && j + distances.get(start2).get(next2) < 26) {
                        int sum = bfs(next2, new HashSet<>(toVisit), j + distances.get(start2).get(next2), 26);

                        if (sum > maxSum) {
                            maxSum = sum;
                        }
                    }
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
        for (Cave next : toVisit) {
            int distance = distances.get(start).get(next);

            if (i + distance < limit && next.flowRate != 0) {
                int sum = bfs(next, new HashSet<>(toVisit), i + distance, limit);
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
