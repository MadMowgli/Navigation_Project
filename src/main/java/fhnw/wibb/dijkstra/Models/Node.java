package fhnw.wibb.dijkstra.Models;

import fhnw.wibb.util.iNode;

import java.util.ArrayList;
import java.util.Comparator;

public class Node implements iNode, Comparable<Node> {

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

    // Getters & Setters
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
