package fhnw.wibb.breadth_first.Models;

import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.*;

public class BreadthFirst {

    private final static String measurementName = "BreadthFirst";

    // Methods
    public static Results<Node> breadthFirstFind(Node start, Node destination, WatchDog watchDog) {

        // Initiate stuff
        Queue<Node> queue = new LinkedList<>();     // Contains nodes that we need to visit
        HashSet<Node> visited = new HashSet<>();    // Contains nodes that we have visited already

        // Initial step: Add starting node to queue
        watchDog.startMeasurement(measurementName);
        queue.add(start);

        // Dequeue a node, add it to the visited set, add all neighbors to the queue
        while(!queue.isEmpty()) {
            Node currentNode = queue.remove();
            visited.add(currentNode);

            for(Node neighbour : currentNode.getNeighbours()) {
                if(!visited.contains(neighbour)) { neighbour.setParent(currentNode); }   // Set the current node as the parent of each neighbour. IMPORTANT: Neighbour MUST NOT have been visited yet! We end up in circular parenting otherwise (lol)
                if(neighbour == destination) { return constructPath(start, destination, watchDog); }  // If one of the neighbours is the destination, stop the search and return the shortest path
                if(!visited.contains(neighbour) && !queue.contains(neighbour)) { queue.add(neighbour); }  // If we end up here, we've found a new node -> add it to the queue
            }
        }

        return null;
    }

    private static Results<Node> constructPath(Node start, Node destination, WatchDog watchDog) {
        ArrayList<Node> path = new ArrayList<>();   // This arraylist contains the nodes of the shortest path
        Node currentNode = destination;     // We "walk back" from the destination, following the parents path

        while (currentNode != null) {   // currentNode will be null when we end up at the start node, because this has no parent
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path); // Reverse the path to get the correct order from start to destination

        // Algorithm stops working here
        watchDog.stopMeasurement(measurementName);

        return new Results<>(path, watchDog);
    }

}
