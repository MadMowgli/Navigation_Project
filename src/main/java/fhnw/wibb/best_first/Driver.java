package fhnw.wibb.best_first;

import fhnw.wibb.best_first.Models.BestFirst;
import fhnw.wibb.best_first.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        // Create a watchdog object to time things
        WatchDog watchDog = new WatchDog("BestFirst");
        watchDog.snapShotTotalMemory();     // See how much  memory we have at total

        // Load data from the csv files
        watchDog.snapShotFreeMemory("BeforeLoadingData");
        ArrayList<Node> nodeList = Loader.loadBestFirstNodes();
        watchDog.snapShotFreeMemory("AfterLoadingData");
//        Node start = nodeList.get(3);
//        Node destination = nodeList.get(183);

        for(int i = 1; i < 100; i++) { {
            Node start = nodeList.get(i);
            Node end = nodeList.get(nodeList.size() - i);

            watchDog.snapShotFreeMemory("BeforeAlgorithm");
            Results<Node> results = BestFirst.findShortestPath(start, end, watchDog);
            watchDog.snapShotFreeMemory("AfterAlgorithm");
            results.writeToCSV("BestFirst.csv");
        }}


//        watchDog.snapShotFreeMemory("BeforeAlgorithm");
//        Results<Node> results = BestFirst.findShortestPath(start, destination, watchDog);
//        watchDog.snapShotFreeMemory("AfterAlgorithm");
//
//        System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
//        System.out.println("///////// STARTING NODE: " + start.getName());
//        System.out.println("///////// ENDING NODE: " + destination.getName());
//        System.out.println("///////// PATH: ");
//        results.getPath().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
//        System.out.println("///////////////////////////////");

    }
}
