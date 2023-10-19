package fhnw.wibb.dijkstra.Models;

import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.*;

/* Dijkstra's algorithm is a greedy graph traversal algorithm that finds the shortest path from a designated
starting node to all other nodes in a weighted graph by iteratively selecting the node with the smallest tentative
distance and updating its neighbors' distances based on the current path. */
public class DijkstraSearch {

    // TODO: Use performance timers here
    public static Results<Node> findShortestPath(Node start, Node destination, WatchDog watchDog) {

        if(start == null){
            throw new IllegalArgumentException("Invalid start node argument. Start node musn't be null.");
        }
        if(destination == null){
            throw new IllegalArgumentException("Invalid destination node argument. Destination node musn't be null.");
        }
        if(watchDog == null){
            throw new IllegalArgumentException("Invalid watchDog argument. WatchDog musn't be null.");
        }

        // Initialize all nodes as unvisited
        ArrayList<Node> neighbours = start.getNeighbours();
        for (Node node : neighbours) {
            node.setUnvisited();
        }

        // Set the start node's distance to 0
        start.setDistance(0);

        // Create a priority queue to store the nodes to be visited
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));

        // Algorithm starts running here, so we start counting
        watchDog.startTime();
        queue.add(start);

        while (!queue.isEmpty()) {

            // Get the node with the shortest distance from the queue
            Node currentNode = queue.poll();

            // If the current node is the destination node, return the shortest path
            if (currentNode == destination) {
                return getShortestPath(start, currentNode, watchDog);
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
        return null;
    }

    private static Results<Node> getShortestPath(Node start, Node currentNode, WatchDog watchDog) {

        // Create a stack to store nodes in reverse order of the shortest path
        Stack<Node> shortestPath = new Stack<>();
        start.setParent(null);

        // Traverse the path by following parent pointers until reaching the start node
        while (currentNode != null) {
            shortestPath.push(currentNode);
            currentNode = currentNode.getParent();
        }

        // Create an ArrayList to store nodes in correct order
        ArrayList<Node> shortestPathList = new ArrayList<>();

        // Pop nodes from the stack and add them to the ArrayList to get the correct order
        while (!shortestPath.isEmpty()) {
            shortestPathList.add(shortestPath.pop());
        }

        // Return the list representing the shortest path
        watchDog.stopTime();
        return new Results<>(shortestPathList, watchDog);
    }

}

