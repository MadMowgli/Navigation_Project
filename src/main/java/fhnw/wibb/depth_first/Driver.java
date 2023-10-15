package fhnw.wibb.depth_first;
import fhnw.wibb.depth_first.Models.DepthFirstSearch;
import fhnw.wibb.depth_first.Models.Node;
import fhnw.wibb.util.Loader;

import java.util.ArrayList;
public class Driver {

    // This is the driver class for the depth  first package. Use it to run the algorithm.
    public static void main(String[] args) {

        ArrayList<Node> nodeList = Loader.loadDepthFirstNodes();
        Node start = nodeList.get(0);
        Node end = nodeList.get(10);
        ArrayList<Node> path = DepthFirstSearch.findPath(start, end);

        System.out.println("/////////////////////////////// DEPTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + end.getName());
        System.out.println("///////// PATH: ");
        path.stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

        System.out.println();
    }

}