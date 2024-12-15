package year.year2024.day09;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Block> diskMap = parse(new java.util.Scanner(file).nextLine().toCharArray());
        int lastFileSpacePos = diskMap.size() - 1;

        int firstEmptySpacePos;
        while ((firstEmptySpacePos = getNextEmptySpacePos(diskMap, 1)) < lastFileSpacePos) {
            Block emptySpace = diskMap.get(firstEmptySpacePos);

            while ((lastFileSpacePos = getLastFileSpacePos(diskMap, diskMap.size() - 1)) > firstEmptySpacePos
                    && emptySpace.length > 0) {
                Block fileSpace = diskMap.get(lastFileSpacePos);

                if (emptySpace.length < fileSpace.length) {
                    diskMap.add(diskMap.indexOf(emptySpace), new Block(fileSpace.id, emptySpace.length));
                    diskMap.remove(emptySpace);
                    fileSpace.length -= emptySpace.length;
                    break;
                } else {
                    diskMap.set(diskMap.indexOf(fileSpace), new Block(0, fileSpace.length));
                    diskMap.add(diskMap.indexOf(emptySpace), fileSpace);
                    emptySpace.length -= fileSpace.length;
                }
            }
        }

        return calcChecksum(diskMap);
    }

    private long sumUpTo(int n) {
        return (long) n * (n + 1) / 2;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Block> diskMap = parse(new java.util.Scanner(file).nextLine().toCharArray());
        int lastFileSpacePos = getLastFileSpacePos(diskMap, diskMap.size() - 1);

        int firstEmptySpacePos;
        while (lastFileSpacePos > (firstEmptySpacePos = getNextEmptySpacePos(diskMap, 1))) {
            Block fileSpace = diskMap.get(lastFileSpacePos);

            while (firstEmptySpacePos < lastFileSpacePos) {
                Block emptySpace = diskMap.get(firstEmptySpacePos);

                if (emptySpace.length == 0) {
                    diskMap.remove(firstEmptySpacePos);
                    firstEmptySpacePos = getNextEmptySpacePos(diskMap, firstEmptySpacePos);
                    continue;
                }

                if (emptySpace.length < fileSpace.length) {
                    firstEmptySpacePos = getNextEmptySpacePos(diskMap, firstEmptySpacePos + 1);
                    continue;
                }

                if (emptySpace.length > fileSpace.length) {
                    emptySpace.length -= fileSpace.length;
                    diskMap.set(lastFileSpacePos, new Block(0, fileSpace.length));
                    diskMap.add(firstEmptySpacePos, fileSpace);
                    break;
                }

                diskMap.set(lastFileSpacePos, new Block(0, fileSpace.length));
                diskMap.set(firstEmptySpacePos, fileSpace);
                break;
            }

            lastFileSpacePos = getLastFileSpacePos(diskMap, lastFileSpacePos - 1);
        }

        return calcChecksum(diskMap);
    }

    private int getLastFileSpacePos(ArrayList<Block> diskMap, int start) {
        int i;
        for (i = start; i >= 0; i--) {
            Block block = diskMap.get(i);

            if (block.id != 0) {
                break;
            }
        }
        return i;
    }

    private int getNextEmptySpacePos(ArrayList<Block> diskMap, int start) {
        int i;
        for (i = start; i < diskMap.size(); i++) {
            Block block = diskMap.get(i);

            if (block.id == 0 && block.length > 0) {
                break;
            }
        }
        return i;
    }

    private long calcChecksum(ArrayList<Block> diskMap) {
        long checksum = 0;

        int p = -1;
        for (Block block : diskMap) {
            int p_ = p + block.length;
            checksum += block.id * (sumUpTo(p_) - sumUpTo(p));
            p = p_;
        }

        return checksum;
    }

    private ArrayList<Block> parse(char[] cs) {
        ArrayList<Block> blocks = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < cs.length; i++) {
            int length = Integer.parseInt(String.valueOf(cs[i]));

            if (i % 2 == 0) {
                blocks.add(new Block(id, length));
                id++;
            } else {
                blocks.add(new Block(0, length));
            }
        }
        return blocks;
    }

    private static class Block {
        int id;
        int length;

        public Block(int id, int length) {
            this.id = id;
            this.length = length;
        }
    }
}
