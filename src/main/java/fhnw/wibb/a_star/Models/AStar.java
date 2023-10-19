package fhnw.wibb.a_star.Models;

import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.*;

public class AStar {

    private static final String measurementName = "AStar";

    public static Results<Node> aStarSearch(Node start, Node destination, WatchDog watchDog) {

        // Initialize stuff
        start.setgCost(0);
        start.setTotalCost(start.getgCost(), calculateEuclidianDistance(start, destination));

        // The node with the lowest total cost will always be the head of the queue
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getTotalCost));
        priorityQueue.add(start);
        HashSet<Node> visited = new HashSet<>();

        // The algorithm really starts here, we start counting here
        watchDog.startTime();
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            if(currentNode.equals(destination)) return constructPath(currentNode, watchDog);  // Return if we found the destination

            visited.add(currentNode);   // Mark current node as visited
            for (Node neighbour : currentNode.getNeighbours()) {
                if (visited.contains(neighbour)) continue; // Skip if we already visited this neighbour

                // Get the gCost from the current node to the neighbour by looking at the respective edge
                for (Edge edge : currentNode.getEdges()) {
                    if (edge.getA().equals(neighbour) || edge.getB().equals(neighbour)) {
                        double tentative_g = currentNode.getgCost() + edge.getDistance();

                        // Here, check whether the new path to the neighbour is better than the old one.
                        if (tentative_g < neighbour.getgCost()) {
                            neighbour.setParent(currentNode);
                            neighbour.setgCost(tentative_g);
                            neighbour.setTotalCost(neighbour.getgCost(), calculateEuclidianDistance(neighbour, destination));

                            // Updating the priority queue, so the newly set values are reflected
                            if (priorityQueue.contains(neighbour)) {
                                priorityQueue.remove(neighbour);
                            }
                            priorityQueue.add(neighbour);
                        }
                    }
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

    private static Results<Node> constructPath(Node destination, WatchDog watchDog) {
        ArrayList<Node> path = new ArrayList<>();   // This arraylist contains the nodes of the shortest path
        Node currentNode = destination;     // We "walk back" from the destination, following the parents path

        while (currentNode != null) {   // currentNode will be null when we end up at the start node, because this has no parent
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path); // Reverse the path to get the correct order from start to destination

        // The algorithm stops here, we stop the measurement
        watchDog.stopTime();

        return new Results<>(path, watchDog);
    }

}
