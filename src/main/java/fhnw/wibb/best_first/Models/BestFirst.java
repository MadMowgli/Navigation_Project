package fhnw.wibb.best_first.Models;

import java.util.*;

public class BestFirst {

    public static ArrayList<Node> findShortestPath(Node start, Node destination) {

        // Initialize stuff
        HashSet<Node> visited = new HashSet<>();

        // The priority queue will always have the node with the lowest heuristic at its head
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getHeuristicToDestination));
        priorityQueue.add(start);

        // Loop through each node in the priority queue
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();    // Grab the next node
            if(visited.contains(currentNode)) continue; // Skip if already visited
            visited.add(currentNode);

            // If we found the destination node, construct the shortest path by following the path of parents
            if(currentNode.equals(destination)) { return constructPath(currentNode); }

            // Loop over each neighbor of the current node, calculate its heuristic, set the current node as parent and add it to the queue
            for(Node neighbour : currentNode.getNeighbours()) {
                if(!visited.contains(neighbour)) {
                    neighbour.setHeuristicToDestination(calculateEuclidianDistance(neighbour, destination));
                    neighbour.setParent(currentNode);
                    priorityQueue.add(neighbour);
                }
            }
        }

        return null;
    }

    private static double calculateEuclidianDistance(Node node1, Node node2) {
        int node1X = node1.getX();
        int node2X = node2.getX();
        int node1Y = node1.getY();
        int node2Y = node2.getY();

        return Math.sqrt(Math.pow((node1X - node2X), 2) + Math.pow((node1Y - node2Y), 2));
    }

    private static ArrayList<Node> constructPath(Node destination) {
        ArrayList<Node> path = new ArrayList<>();   // This arraylist contains the nodes of the shortest path
        Node currentNode = destination;     // We "walk back" from the destination, following the parents path

        while (currentNode != null) {   // currentNode will be null when we end up at the start node, because this has no parent
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path); // Reverse the path to get the correct order from start to destination

        return path;
    }

}
