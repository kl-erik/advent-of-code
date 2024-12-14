package year.year2024.day09;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        return null;
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

        public Block(int length) {
            this.length = length;
        }
    }

    private static class FileSpace extends Block {
        private final int id;

        public FileSpace(int id, int length) {
            super(length);
            this.id = id;
        }
    }

    private static class EmptySpace extends Block {
        public EmptySpace(int length) {
            super(length);
        }
    }
}
