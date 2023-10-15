package fhnw.wibb.a_star.Models;

public class Edge {

    // Fields
    private Node a;
    private Node b;
    private Double distance;

    // Constructor
    public Edge(Node a, Node b, Double distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }


    // Getters & Setters
    public Node getA() {
        return a;
    }

    public void setA(Node a) {
        this.a = a;
    }

    public Node getB() {
        return b;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
