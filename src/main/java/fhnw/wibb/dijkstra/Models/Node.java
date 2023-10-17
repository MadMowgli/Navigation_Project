package fhnw.wibb.dijkstra.Models;

import java.util.ArrayList;
import java.util.Comparator;

// Node.java

public class Node implements Comparable<Node> {

    private String name;
    private ArrayList<Node> neighbours;
    private Node parent;
    private int distance;
    private boolean visited;
    private int weight;

    public Node(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
        this.visited = false;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setUnvisited() {
        this.visited = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(this.weight, otherNode.weight);
    }

}
