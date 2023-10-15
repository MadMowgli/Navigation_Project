package fhnw.wibb.depth_first;

import java.util.*;

public class DepthFirstSearch {

    public static ArrayList<Node> findPath(Node startNode, Node destinationNode) {

        // Initialize stuff
        Stack<ArrayList<Node>> stack = new Stack<>();
        ArrayList<Node> initialPath = new ArrayList<>();
        initialPath.add(startNode);     // Add starting node to the path, since we begin from here

        stack.push(initialPath);

        while (!stack.isEmpty()) {
            ArrayList<Node> currentPath = stack.pop();
            Node currentNode = currentPath.get(currentPath.size() - 1); // Grabs the last node of the current path

            if (currentNode.equals(destinationNode)) {
                return currentPath; // Path found
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



