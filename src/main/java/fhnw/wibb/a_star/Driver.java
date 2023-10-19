package fhnw.wibb.a_star;

import fhnw.wibb.a_star.Models.AStar;
import fhnw.wibb.a_star.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.ArrayList;

public class Driver {

    // This is the driver class for the a* package. Use it to run the algorithm.
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadAStarNodes();
        Node start = nodeList.get(0);
        Node end = nodeList.get(10);

        // Create a watchdog object to time things
        WatchDog watchDog = new WatchDog("AStar");

        // Search the shortest path using a*
        Results<Node> results = AStar.aStarSearch(start, end, watchDog);
        System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + end.getName());
        System.out.println("///////// PATH: ");
        results.getPath().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

    }

}
