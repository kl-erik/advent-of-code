package year.year2022.day08;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] trees = parse(file);
        boolean[][] visible = initVisible(trees.length, trees[0].length);

        for (int row = 1; row < trees.length - 1; row++) {
            for (int col = 1; col < trees[row].length - 1; col++) {
                int tree = trees[row][col];

                if (tree > trees[row][0]) {
                    trees[row][0] = tree;
                    visible[row][col] = true;
                }

                if (tree > trees[0][col]) {
                    trees[0][col] = tree;
                    visible[row][col] = true;
                }
            }
        }

        for (int row = trees.length - 2; row > 0; row--) {
            for (int col = trees[row].length - 2; col > 0; col--) {
                int tree = trees[row][col];

                if (tree > trees[row][trees.length - 1]) {
                    trees[row][trees.length - 1] = tree;
                    visible[row][col] = true;
                }

                if (tree > trees[trees.length - 1][col]) {
                    trees[trees.length - 1][col] = tree;
                    visible[row][col] = true;
                }
            }
        }

        int visibleTrees = 0;

        for (boolean[] visibleLine : visible) {
            for (boolean visibleTree : visibleLine) {
                if (visibleTree) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

    private static int[][] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<Integer>> treeList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            ArrayList<Integer> treeLine = new ArrayList<>();

            for (char c : scanner.nextLine().toCharArray()) {
                treeLine.add(Integer.parseInt(String.valueOf(c)));
            }

            treeList.add(treeLine);
        }

        int[][] trees = new int[treeList.size()][treeList.get(0).size()];

        for (int i = 0; i < treeList.size(); i++) {
            for (int j = 0; j < treeList.get(i).size(); j++) {
                trees[i][j] = treeList.get(i).get(j);
            }
        }

        return trees;
    }

    private static boolean[][] initVisible(int width, int height) {
        boolean[][] visible = new boolean[height][width];

        for (int i = 0; i < visible.length; i++) {
            visible[i][0] = true;
            visible[i][visible.length - 1] = true;
        }

        for (int i = 0; i < visible[0].length; i++) {
            visible[0][i] = true;
            visible[visible.length - 1][i] = true;
        }
        return visible;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        int[][] trees = parse(file);
        int[][] scenicScores = new int[trees.length][trees[0].length];

        for (int row = 0; row < trees.length; row++) {
            for (int col = 0; col < trees[row].length; col++) {
                int tree = trees[row][col];

                int visibleLeft = 0;
                for (int i = row - 1; i >= 0; i--) {
                    visibleLeft++;

                    if (trees[i][col] >= tree)
                        break;
                }

                int visibleRight = 0;
                for (int i = row + 1; i < trees.length; i++) {
                    visibleRight++;

                    if (trees[i][col] >= tree)
                        break;
                }

                int visibleTop = 0;
                for (int i = col - 1; i >= 0; i--) {
                    visibleTop++;

                    if (trees[row][i] >= tree)
                        break;
                }

                int visibleBot = 0;
                for (int i = col + 1; i < trees[row].length; i++) {
                    visibleBot++;

                    if (trees[row][i] >= tree)
                        break;
                }

                scenicScores[row][col] = visibleLeft * visibleTop * visibleRight * visibleBot;
            }
        }

        int maxScenicScore = 0;

        for (int[] scenicScoreLine : scenicScores) {
            for (int scenicScore : scenicScoreLine) {
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }

        return maxScenicScore;
    }
}
