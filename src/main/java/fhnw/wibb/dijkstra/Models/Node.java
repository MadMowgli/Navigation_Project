package fhnw.wibb.dijkstra.Models;

import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> neighbours;
    private Node parent;

    public Node(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
