package year.year2023.day05;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Map> sources = getSourcesP1(file);
        ArrayList<ArrayList<Map>> maps = parse(file);
        process(maps, sources);
        return getMin(sources);
    }

    private static long getMin(ArrayList<Map> sources) {
        long min = Long.MAX_VALUE;

        for (Map source : sources) {
            if (source.destinationStart < min) {
                min = source.destinationStart;
            }
        }

        return min;
    }

    private static void process(ArrayList<ArrayList<Map>> maps, ArrayList<Map> sources) {
        for (ArrayList<Map> destinations : maps) {
            for (int i = 0; i < sources.size(); i++) {
                Map source = sources.get(i);
                ArrayList<Map> newSources = new ArrayList<>();

                for (Map destination : destinations) {
                    if (destination.isDestinationOfAll(source)) {
                        source.setDestination(destination);
                        break;
                    } else if (destination.isDestinationOfSome(source)) {
                        newSources.addAll(source.setDestinationAndSplit(destination));
                        break;
                    }
                }

                sources.addAll(newSources);
            }
        }
    }

    private ArrayList<Map> getSourcesP1(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<Map> seedMaps = new ArrayList<>();
            long[] seeds = toLongs(scanner.nextLine().substring(7).split("\\s+"));

            for (long seed : seeds) {
                seedMaps.add(new Map(seed, seed, 0));
            }

            return seedMaps;
        }
    }

    private ArrayList<ArrayList<Map>> parse(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            scanner.nextLine();

            ArrayList<ArrayList<Map>> almanac = new ArrayList<>();

            while (scanner.hasNext()) {
                scanner.nextLine();

                ArrayList<Map> maps = new ArrayList<>();
                String line;
                while (scanner.hasNext() && !(line = scanner.nextLine()).isEmpty()) {
                    long[] longs = toLongs(line.split("\\s+"));
                    maps.add(new Map(longs[0], longs[1], longs[2]));
                }
                almanac.add(maps);
            }

            return almanac;
        }
    }

    private long[] toLongs(String[] strings) {
        long[] longs = new long[strings.length];

        for (int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(strings[i]);
        }

        return longs;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Map> sources = getSourcesP2(file);
        ArrayList<ArrayList<Map>> maps = parse(file);
        process(maps, sources);
        return getMin(sources);
    }

    private ArrayList<Map> getSourcesP2(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<Map> seedMaps = new ArrayList<>();
            long[] seeds = toLongs(scanner.nextLine().substring(7).split("\\s+"));

            for (int i = 0; i < seeds.length - 1; i += 2) {
                seedMaps.add(new Map(seeds[i], seeds[i], seeds[i + 1]));
            }

            return seedMaps;
        }
    }

    private static class Map {
        private long destinationStart;
        private long destinationEnd;
        private long sourceStart;
        private long sourceEnd;

        public Map(long destination, long source, long range) {
            destinationStart = destination;
            destinationEnd = destination + range;
            sourceStart = source;
            sourceEnd = sourceStart + range;
        }

        public boolean isDestinationOfAll(Map other) {
            return sourceStart <= other.destinationStart && sourceEnd >= other.destinationEnd;
        }

        public void setDestination(Map other) {
            long diff = other.destinationStart - other.sourceStart;
            destinationStart += diff;
            destinationEnd += diff;
        }

        public boolean isDestinationOfSome(Map other) {
            return sourceStart < other.destinationStart && sourceEnd >= other.destinationStart
                    || sourceStart <= other.destinationEnd && sourceEnd > other.destinationEnd;
        }

        public ArrayList<Map> setDestinationAndSplit(Map other) {
            ArrayList<Map> newSources = new ArrayList<>();

            if (destinationStart < other.sourceStart) {
                long range = other.sourceStart - destinationStart - 1;
                Map newSource = new Map(destinationStart, sourceStart, range);
                destinationStart = newSource.destinationEnd + 1;
                sourceStart = newSource.sourceEnd + 1;
                newSources.add(newSource);
            }

            if (destinationEnd > other.sourceEnd) {
                // long range = sourceEnd - (destinationEnd - other.sourceEnd) - 1;
                long range = destinationEnd - other.sourceEnd - 1;
                Map newSource = new Map(destinationEnd - range, sourceEnd - range, range);
                destinationEnd = newSource.destinationStart - 1;
                sourceEnd = newSource.sourceStart - 1;
                newSources.add(newSource);
            }

            setDestination(other);
            return newSources;
        }
    }
}
