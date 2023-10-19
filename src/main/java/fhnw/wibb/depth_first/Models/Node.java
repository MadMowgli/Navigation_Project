package fhnw.wibb.depth_first.Models;
import fhnw.wibb.util.iNode;

import java.util.ArrayList;

public class Node implements iNode, Comparable<Node> {

    // Fields
    private String name;
    private Node parent;
    private ArrayList<Node> neighbours;

    public Node(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    // Getters & Setters

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(obj.getClass() != this.getClass()) { return false; }

        Node comprarer = (Node) obj;
        return this.name.equals(comprarer.name);
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.name.compareTo(otherNode.name);
    }
}
