package year.year2024.day09;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Block> diskMap = parse(new java.util.Scanner(file).nextLine().toCharArray());

        long checksum = 0;

        int p_ = -1;
        int p = p_;

        int j = diskMap.get(diskMap.size() - 1) instanceof FileSpace ? diskMap.size() - 1 : diskMap.size() - 2;
        for (int i = 0; i < diskMap.size(); i++) {
            Block block = diskMap.get(i);

            if (block instanceof FileSpace) {
                FileSpace fileSpace = (FileSpace) block;
                p_ = p + fileSpace.length;
                checksum += fileSpace.id * (sumUpTo(p_) - sumUpTo(p));
                p = p_;
            } else {
                EmptySpace emptySpace = (EmptySpace) block;

                while (emptySpace.length > 0 && j > i) {
                    FileSpace fileSpace = (FileSpace) diskMap.get(j);

                    if (fileSpace.length == 0) {
                        j -= 2;
                        continue;
                    }

                    if (fileSpace.length <= emptySpace.length) {
                        p_ = p + fileSpace.length;
                        checksum += fileSpace.id * (sumUpTo(p_) - sumUpTo(p));
                        emptySpace.length -= fileSpace.length;
                        fileSpace.length = 0;
                    } else {
                        p_ = p + emptySpace.length;
                        checksum += fileSpace.id * (sumUpTo(p_) - sumUpTo(p));
                        fileSpace.length -= emptySpace.length;
                        emptySpace.length = 0;
                    }

                    p = p_;
                }
            }
        }

        return checksum;
    }

    private long sumUpTo(int n) {
        return (long) n * (n + 1) / 2;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Block> diskMap = parse(new java.util.Scanner(file).nextLine().toCharArray());
        Set<FileSpace> visited = new HashSet<>();

        int lastFileSpacePos = getLastFilePos(diskMap, visited, diskMap.size() - 1);
        int firstEmptySpacePos = getNextEmptySpacePos(diskMap, 0);

        while (lastFileSpacePos > firstEmptySpacePos) {
            FileSpace fileSpace = (FileSpace) diskMap.get(lastFileSpacePos);

            while (firstEmptySpacePos < lastFileSpacePos) {
                EmptySpace emptySpace = (EmptySpace) diskMap.get(firstEmptySpacePos);

                if (emptySpace.length == 0) {
                    diskMap.remove(firstEmptySpacePos);
                    firstEmptySpacePos = getNextEmptySpacePos(diskMap, firstEmptySpacePos);
                    continue;
                }

                if (emptySpace.length < fileSpace.length) {
                    firstEmptySpacePos = getNextEmptySpacePos(diskMap, firstEmptySpacePos + 1);
                    continue;
                }

                emptySpace.length -= fileSpace.length;

                diskMap.set(lastFileSpacePos, new EmptySpace(fileSpace.length));
                diskMap.add(firstEmptySpacePos, fileSpace);
                visited.add(fileSpace);

                break;
            }

            lastFileSpacePos = getLastFilePos(diskMap, visited, lastFileSpacePos - 1);
            firstEmptySpacePos = getNextEmptySpacePos(diskMap, 0);
        }

        long checksum = 0;

        int p = -1;

        for (Block block : diskMap) {
            int p_ = p + block.length;
            checksum += block.id * (sumUpTo(p_) - sumUpTo(p));
            p = p_;
        }

        return checksum;
    }

    private int getLastFilePos(ArrayList<Block> diskMap, Set<FileSpace> visited, int start) {
        int i;
        for (i = start; i >= 0; i--) {
            Block block = diskMap.get(i);

            if (block instanceof FileSpace && !visited.contains(block)) {
                break;
            }
        }
        return i;
    }

    private int getNextEmptySpacePos(ArrayList<Block> diskMap, int start) {
        int i;
        for (i = start; i < diskMap.size(); i++) {
            Block block = diskMap.get(i);

            if (block instanceof EmptySpace && block.length > 0) {
                break;
            }
        }
        return i;
    }

    private ArrayList<Block> parse(char[] cs) {
        ArrayList<Block> blocks = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < cs.length; i++) {
            int length = Integer.parseInt(String.valueOf(cs[i]));

            if (i % 2 == 0) {
                blocks.add(new FileSpace(id, length));
                id++;
            } else {
                blocks.add(new EmptySpace(length));
            }
        }
        return blocks;
    }

    private abstract static class Block {
        int length;
        int id;

        public Block(int length, int id) {
            this.length = length;
            this.id = id;
        }
    }

    private static class FileSpace extends Block {
        public FileSpace(int id, int length) {
            super(length, id);
        }
    }

    private static class EmptySpace extends Block {
        public EmptySpace(int length) {
            super(length, 0);
        }
    }
}
