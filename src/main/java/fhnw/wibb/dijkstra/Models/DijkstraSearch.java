package fhnw.wibb.dijkstra.Models;

// DijkstraSearch.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraSearch {

    public static ArrayList<Node> findShortestPath(Node start, Node destination) {

        // Initialize all nodes as unvisited
        for (Node node : start.getNeighbours()) {
            node.setUnvisited();
        }

        // Set the start node's distance to 0
        start.setDistance(0);

        // Create a priority queue to store the nodes to be visited
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            // Get the node with the shortest distance from the queue
            Node currentNode = queue.poll();

            // If the current node is the destination node, return the shortest path
            if (currentNode == destination) {
                return getShortestPath(currentNode);
            }

            // Mark the current node as visited
            currentNode.setVisited();

            // Relax all the edges from the current node
            for (Node neighbour : currentNode.getNeighbours()) {
                if (!neighbour.isVisited()) {
                    int newDistance = currentNode.getDistance() + neighbour.getWeight();
                    if (newDistance < neighbour.getDistance()) {
                        neighbour.setDistance(newDistance);
                        neighbour.setParent(currentNode);
                        queue.add(neighbour);
                    }
                }
            }
        }

        // If the destination node is not reachable, return an empty list
        return new ArrayList<>();
    }

    private static ArrayList<Node> getShortestPath(Node currentNode) {

        Stack<Node> shortestPath = new Stack<>();
        while (currentNode != null) {
            shortestPath.push(currentNode);
            currentNode = currentNode.getParent();
        }

        ArrayList<Node> shortestPathList = new ArrayList<>();
        while (!shortestPath.isEmpty()) {
            shortestPathList.add(shortestPath.pop());
        }

        return shortestPathList;
    }

}

