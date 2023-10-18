package fhnw.wibb.depth_first.Models;

import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.*;

public class DepthFirstSearch {

    private static final String measurementName = "DepthFirst";

    public static Results<Node> findPath(Node startNode, Node destinationNode, WatchDog watchDog) {

        // Initialize stuff
        Stack<ArrayList<Node>> stack = new Stack<>();
        ArrayList<Node> initialPath = new ArrayList<>();

        // Algorithm really starts running from here, so we start counting
        watchDog.startMeasurement(measurementName);

        initialPath.add(startNode);     // Add starting node to the path, since we begin from here

        stack.push(initialPath);

        while (!stack.isEmpty()) {
            ArrayList<Node> currentPath = stack.pop();
            Node currentNode = currentPath.get(currentPath.size() - 1); // Grabs the last node of the current path

            if (currentNode.equals(destinationNode)) {
                watchDog.stopMeasurement(measurementName);
                return new Results(currentPath, watchDog); // Path found
            }

            for (Node neighbour : currentNode.getNeighbours()) {
                if (!currentPath.contains(neighbour)) {
                    ArrayList<Node> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbour);
                    stack.push(newPath);
                }
            }
        }

        return null; // Path not found


    }
}



