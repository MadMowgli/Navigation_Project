package fhnw.wibb.breadth_first.Models;

import java.util.*;

public class BreadthFirst {

    // Methods
    public static ArrayList<Node> breadthFirst(Node start, Node destination) {

        // Initiate stuff
        ArrayList<Node> shortestPath = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();     // Contains nodes that we need to visit
        HashSet<Node> visited = new HashSet<>();    // Contains nodes that we have visited already

        // First step: Add starting node to queue
        queue.add(start);

        // Dequeue a node, add it to the visited set, add all neighbors to the queue
        while(!queue.isEmpty()) {
            Node currentNode = queue.remove();
            visited.add(currentNode);

            for(Node neighbour : currentNode.getNeighbours()) {
                neighbour.setParent(currentNode);   // Set the current node as the parent of each neighbour
                if(!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        // TODO: Find shortest path

        return shortestPath;
    }

}
