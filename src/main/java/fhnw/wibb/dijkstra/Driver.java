package fhnw.wibb.dijkstra;

import fhnw.wibb.dijkstra.Models.DijkstraSearch;
import fhnw.wibb.dijkstra.Models.Node;
import fhnw.wibb.util.Loader;
import java.lang.reflect.Array;
import java.util.ArrayList;

// Driver.java

public class Driver {

    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<fhnw.wibb.dijkstra.Models.Node> nodeList = Loader.loadDijkstraNodes();
        fhnw.wibb.dijkstra.Models.Node start = nodeList.get(0);
        fhnw.wibb.dijkstra.Models.Node destination = nodeList.get(10);

        // Find the shortest path using Dijkstra's algorithm
        ArrayList<Node> shortestPath = DijkstraSearch.findShortestPath(start, destination);

        // Print the shortest path
        System.out.println("/////////////////////////////// DIJKSTRA'S SHORTEST PATH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + destination.getName());
        System.out.println("///////// PATH: ");
        shortestPath.stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");
    }
}


