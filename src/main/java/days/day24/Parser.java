package days.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    public static Node[][][] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); // skip first line
        ArrayList<ArrayList<ArrayList<Character>>> valleyList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            char[] chars = scanner.nextLine().toCharArray();
            if (!scanner.hasNextLine()) break; // skip last line
            ArrayList<ArrayList<Character>> list = getCharacters(chars);
            valleyList.add(list);
        }

        ArrayList<Character>[][][] valleyStates = generateAllStates(toArray(valleyList));
        Node[][][] nodes = toNodes(valleyStates);
        connect(nodes);
        return nodes;
    }

    private static void connect(Node[][][] nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            connect(nodes[i], nodes[i+1]);
        }
        
        connect(nodes[nodes.length - 1], nodes[0]);
    }

    private static void connect(Node[][] nodeLevel1, Node[][] nodeLevel2) {
        for (int j = 0; j < nodeLevel1.length; j++) {
            for (int k = 0; k < nodeLevel1[j].length; k++) {
                if (nodeLevel1[j][k] != null) {
                    if (nodeLevel2[j][k] != null) {
                        nodeLevel1[j][k].connect(nodeLevel2[j][k]);
                    }

                    if (j > 0 && nodeLevel2[j-1][k] != null) {
                        nodeLevel1[j][k].connect(nodeLevel2[j-1][k]);
                    }

                    if (j < nodeLevel2.length - 1 && nodeLevel2[j+1][k] != null) {
                        nodeLevel1[j][k].connect(nodeLevel2[j+1][k]);
                    }

                    if (k > 0 && nodeLevel2[j][k-1] != null) {
                        nodeLevel1[j][k].connect(nodeLevel2[j][k-1]);
                    }

                    if (k < nodeLevel2[j].length - 1 && nodeLevel2[j][k+1] != null) {
                        nodeLevel1[j][k].connect(nodeLevel2[j][k+1]);
                    }
                }
            }
        }
    }

    private static Node[][][] toNodes(ArrayList<Character>[][][] valleyStates) {
        Node[][][] nodes = new Node[valleyStates.length][valleyStates[0].length][valleyStates[0][0].length];

        for (int i = 0; i < valleyStates.length; i++) {
            ArrayList<Character>[][] valleyState = valleyStates[i];

            Node[][] nodeLevel = new Node[valleyState.length][valleyState[0].length];
            for (int j = 0; j < nodeLevel.length; j++) {
                for (int k = 0; k < nodeLevel[j].length; k++) {
                    if (valleyState[j][k].isEmpty()) {
                        nodeLevel[j][k] = new Node();
                    }
                }
            }

            nodes[i] = nodeLevel;
        }

        return nodes;
    }

    private static ArrayList<Character>[][][] generateAllStates(ArrayList<Character>[][] valley) {
        ArrayList<ArrayList<Character>[][]> valleyStates = new ArrayList<>();
        int i = 0;
        valleyStates.add(i, valley);
        for (i = 1; i <= Math.max(valley.length, valley[0].length); i++) {
            valley = update(valley);
            valleyStates.add(i, valley);
        }
        while (true) {
            valley = update(valley);
            if (identical(valley, valleyStates.get(0))) break;
            valleyStates.add(i, valley);
            i++;
        }
        return valleyStates.toArray(new ArrayList[0][][]);
    }

    private static boolean identical(ArrayList<Character>[][] valley1, ArrayList<Character>[][] valley2) {
        for (int i = 0; i < valley1.length; i++) {
            for (int j = 0; j < valley1[i].length; j++) {
                if (valley1[i][j].size() != valley2[i][j].size()) {
                    return false;
                } else {
                    for (int k = 0; k < valley1[i][j].size(); k++) {
                        if (valley1[i][j].get(k) != valley2[i][j].get(k)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static ArrayList<ArrayList<Character>> getCharacters(char[] chars) {
        ArrayList<ArrayList<Character>> list = new ArrayList<>();
        for (char c : chars) {
            if (c != '#') {
                if (c != '.') {
                    list.add(new ArrayList<>(List.of(c)));
                } else {
                    list.add(new ArrayList<>());
                }
            }
        }
        return list;
    }

    private static ArrayList<Character>[][] toArray(ArrayList<ArrayList<ArrayList<Character>>> valleyList) {
        ArrayList<ArrayList<Character>[]> valley = new ArrayList<>();
        for (ArrayList<ArrayList<Character>> list : valleyList){
            valley.add(list.toArray(new ArrayList[0]));
        }
        return valley.toArray(new ArrayList[0][]);
    }

    private static ArrayList<Character>[][] update(ArrayList<Character>[][] valley) {
        ArrayList<Character>[][] updatedValley = initValley(valley.length, valley[0].length);

        for (int i = 0; i < valley.length; i++) {
            for (int j = 0; j < valley[i].length; j++) {
                for (char c : valley[i][j]) {
                    switch (c) {
                        case '^':
                            if (i > 0) {
                                updatedValley[i - 1][j].add(c);
                            } else {
                                updatedValley[valley.length - 1][j].add(c);
                            }
                            break;
                        case 'v':
                            if (i < valley.length - 1) {
                                updatedValley[i + 1][j].add(c);
                            } else {
                                updatedValley[0][j].add(c);
                            }
                            break;
                        case '<':
                            if (j > 0) {
                                updatedValley[i][j - 1].add(c);
                            } else {
                                updatedValley[i][valley[i].length - 1].add(c);
                            }
                            break;
                        case '>':
                            if (j < valley[i].length - 1) {
                                updatedValley[i][j + 1].add(c);
                            } else {
                                updatedValley[i][0].add(c);
                            }
                    }
                }
            }
        }

        return updatedValley;
    }

    private static ArrayList<Character>[][] initValley(int lengthY, int lengthX) {
        ArrayList<Character>[][] valley = new ArrayList[lengthY][lengthX];
        for (int i = 0; i < valley.length; i++) {
            for (int j = 0; j < valley[i].length; j++) {
                valley[i][j] = new ArrayList<>();
            }
        }
        return valley;
    }
}
