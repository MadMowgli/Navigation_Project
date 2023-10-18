package fhnw.wibb.dijkstra;

import fhnw.wibb.dijkstra.Models.DijkstraSearch;
import fhnw.wibb.dijkstra.Models.Node;
import fhnw.wibb.util.Loader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadDijkstraNodes();
        Node start = nodeList.get(0);
        Node destination = nodeList.get(12);
        System.out.println(destination.getName());
        // Find the shortest path using Dijkstra's algorithm
        DijkstraSearch.findShortestPath(start, destination);

        // Print the shortest path
        System.out.println("/////////////////////////////// DIJKSTRA'S SHORTEST PATH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + destination.getName());
        System.out.println("///////// PATH: ");
        DijkstraSearch.getNodesList(start, destination, nodeList).stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");
    }
}



