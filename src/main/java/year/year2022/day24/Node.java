package year.year2022.day24;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private final Set<Node> neighbours = new HashSet<>();

    void connect(Node node) {
        this.neighbours.add(node);
    }

    public Set<Node> getNeighbours() {
        return neighbours;
    }
}
