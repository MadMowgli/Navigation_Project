package fhnw.wibb.a_star.Models;

import java.util.ArrayList;

public class Node {

    // Fields
    private String name;
    private int x;
    private int y;
    private double hCost;   // The heuristic -> Euclidean distance to the destination
    private double gCost;   // The sum of the costs of all edges from the start to the current node
    private double totalCost;   // gCost + fCost
    private Node parent;
    private ArrayList<Edge> edges;
    private ArrayList<Node> neighbours;


    public Node(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.gCost = Double.MAX_VALUE;
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

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
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

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double getTotalCost() {
        return this.gCost + this.hCost;
    }

    public void setTotalCost(double gCost, double hCost) {
        this.hCost = hCost;
        this.totalCost = gCost + hCost;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(obj.getClass() != this.getClass()) { return false; }

       Node comparer = (Node) obj;
        return this.name.equals(comparer.getName());
    }
}
