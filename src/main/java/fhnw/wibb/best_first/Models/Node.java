package fhnw.wibb.best_first.Models;

import fhnw.wibb.util.iNode;

import java.util.ArrayList;

public class Node implements iNode, Comparable<Node> {

    // Fields
    private String name;
    private int x;
    private int y;
    private double heuristicToDestination;
    private Node parent;
    private ArrayList<Node> neighbours;

    // Constructor
    public Node(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    // Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getHeuristicToDestination() {
        return heuristicToDestination;
    }

    public void setHeuristicToDestination(double heuristicToDestination) {
        this.heuristicToDestination = heuristicToDestination;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(obj.getClass() != this.getClass()) { return false; }

        Node comparer = (Node) obj;
        return this.name.equals(comparer.getName());
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.name.compareTo(otherNode.name);
    }
}
