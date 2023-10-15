package fhnw.wibb.a_star.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class AStar {

    public static ArrayList<Node> aStarSearch(Node start, Node destination) {

        // Initialize stuff
        ArrayList<Node> shortestPath = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();     // Contains nodes that we need to visit
        HashSet<Node> visited = new HashSet<>();    // Contains nodes that we have visited already
        shortestPath.add(start);
        visited.add(start);
        double distanceTraveled;

        // Calculate ED from start to dest and store it inside of the start node
        start.setEdToDestination(calculateEuclidianDistance(start, destination));

        // Calculate ED for all neighbours
        for(Node n : start.getNeighbours()) {
            n.setEdToDestination(calculateEuclidianDistance(n, destination));
        }

        return shortestPath;
    }

    private static double calculateEuclidianDistance(Node node1, Node node2) {
        int node1X = node1.getX();
        int node2X = node2.getX();
        int node1Y = node1.getY();
        int node2Y = node2.getY();

        return Math.sqrt(Math.pow((node1X - node2X), 2) + Math.pow((node1Y - node2Y), 2));
    }

}
