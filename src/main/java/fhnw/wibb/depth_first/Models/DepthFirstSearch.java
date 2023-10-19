package fhnw.wibb.depth_first.Models;

import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.*;

public class DepthFirstSearch {

    private static final String measurementName = "DepthFirst";

    public static Results<Node> findPath(Node startNode, Node destinationNode, WatchDog watchDog) {

        if(startNode == null){
            throw new IllegalArgumentException("Invalid start node argument. Start node musn't be null.");
        }
        if(destinationNode == null){
            throw new IllegalArgumentException("Invalid destination node argument. Destination node musn't be null.");
        }
        if(watchDog == null){
            throw new IllegalArgumentException("Invalid watchDog argument. WatchDog musn't be null.");
        }

        // Initialize stuff
        Stack<ArrayList<Node>> stack = new Stack<>();
        Set<Node> visited = new HashSet<>(); // Track visited nodes

        ArrayList<Node> initialPath = new ArrayList<>();
        initialPath.add(startNode);

        stack.push(initialPath);
        visited.add(startNode);

        watchDog.startTime();

        while (!stack.isEmpty()) {
            ArrayList<Node> currentPath = stack.pop();
            Node currentNode = currentPath.get(currentPath.size() - 1);

            if (currentNode.equals(destinationNode)) {
                watchDog.stopTime();
                return new Results(currentPath, watchDog);
            }

            for (Node neighbour : currentNode.getNeighbours()) {
                if (!visited.contains(neighbour)) {
                    ArrayList<Node> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbour);
                    stack.push(newPath);
                    visited.add(neighbour); // Mark the node as visited
                }
            }
        }

        return null; // Path not found


    }
}



