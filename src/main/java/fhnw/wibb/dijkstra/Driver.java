package fhnw.wibb.dijkstra;

import fhnw.wibb.dijkstra.Models.DijkstraSearch;
import fhnw.wibb.dijkstra.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        // Load data from the csv files
        WatchDog watchDog = new WatchDog("Dijkstra");
        watchDog.snapShotTotalMemory();     // See how much  memory we have at total

        watchDog.snapShotFreeMemory("BeforeLoadingData");
        ArrayList<Node> nodeList = Loader.loadDijkstraNodes();
        watchDog.snapShotFreeMemory("AfterLoadingData");


        Node start = nodeList.get(0);
        Node destination = nodeList.get(12);

        // Find the shortest path using Dijkstra's algorithm
        Results<Node> results = DijkstraSearch.findShortestPath(start, destination, watchDog);

        // Print the shortest path
        System.out.println("/////////////////////////////// DIJKSTRA'S SHORTEST PATH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + destination.getName());
        System.out.println("///////// PATH: ");
        results.getPath().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");
    }
}



