package fhnw.wibb.depth_first;
import java.util.ArrayList;

public class Node {

    // Fields
    private String name;
    private ArrayList<Node> nodeNeighbours;

    public Node(String name) {
        this.name = name;
        this.nodeNeighbours = new ArrayList<>();
    }

    // Getters & Setters

    public ArrayList<Node> getNodeNeighbours() {
        return nodeNeighbours;
    }

    public void setNodeNeighbours(ArrayList<Node> nodeNeighbours) {
        this.nodeNeighbours = nodeNeighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
