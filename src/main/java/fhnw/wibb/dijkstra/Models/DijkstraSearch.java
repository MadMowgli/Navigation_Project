package fhnw.wibb.dijkstra.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

/* Dijkstra's algorithm is a greedy graph traversal algorithm that finds the shortest path from a designated
starting node to all other nodes in a weighted graph by iteratively selecting the node with the smallest tentative
distance and updating its neighbors' distances based on the current path. */
public class DijkstraSearch {

    public static ArrayList<Node> findShortestPath(Node start, Node destination) {

        // Initialize all nodes as unvisited
        ArrayList<Node> neighbours = start.getNeighbours();
        for (Node node : neighbours) {
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

        // Create a stack to store nodes in reverse order of the shortest path
        Stack<Node> shortestPath = new Stack<>();

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
        return shortestPathList;
    }

    public static ArrayList<Node> getNodesList(fhnw.wibb.dijkstra.Models.Node start, fhnw.wibb.dijkstra.Models.Node destination, ArrayList<Node> nodesList) {

        // Shuffle the input nodesList to introduce randomness for scenario variation
        Collections.shuffle(nodesList);

        // Create a new random number generator to simulate different scenarios
        Random rand = new Random();

        // Generate a random path size between 3 and 6 for testing robustness and variability
        int pathSize = rand.nextInt((6 - 3) + 1) + 3;

        // Create a new ArrayList called nList containing a sublist of nodes from nodesList with a random size
        ArrayList<Node> nList =  new ArrayList<Node>(nodesList.subList(0, pathSize));

        // Add the start node at the beginning of the list for simulation purposes
        nList.add(0, start);

        // Add the destination node at the end of the list to avoid predictability
        nList.add(nList.size(), destination);

        // Return the modified list containing the start, random nodes, and destination for testing and simulation
        return nList;
    }

}

