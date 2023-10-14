package fhnw.wibb.depth_first;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class DepthFirstSearch {

    public static List<Node> depthFirstSearch(Node startNode, String targetNodeName, Set<Node> visited) {
        if (startNode == null) return null;
        visited.add(startNode);

        if (startNode.getName().equals(targetNodeName)) {
            List<Node> path = new ArrayList<>();
            path.add(startNode);
            return path;
        }

        for (Node neighbor : startNode.getNeighbours()) {
            if (!visited.contains(neighbor)) {
                List<Node> path = depthFirstSearch(neighbor, targetNodeName, visited);
                if (path != null) {
                    path.add(0, startNode);  // Add the current node to the path
                    return path;
                }
            }
        }

        return null;
    }


}



