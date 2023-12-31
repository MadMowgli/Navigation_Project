package fhnw.wibb.best_first;

import fhnw.wibb.best_first.Models.BestFirst;
import fhnw.wibb.best_first.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

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


        for (int i = 1; i < 11; i++) {
            {
                Node start = nodeList.get(i);
                Node end = nodeList.get(nodeList.size() - i);

                try {
                    watchDog.snapShotFreeMemory("BeforeAlgorithm");
                    Results<Node> results = BestFirst.findShortestPath(start, end, watchDog);
                    watchDog.snapShotFreeMemory("AfterAlgorithm");
                    results.writeToCSV("Results_BestFirst_Small.csv");
                } catch (Exception e) {
                    System.out.println("No path found between: " + start.getName() + " and " + end.getName());
                }

            }
        }


    }

//        ArrayList<Node> nodeList = Loader.loadBestFirstNodes();
//        WatchDog watchDog = new WatchDog("BestFirst");
//
//        Node start = nodeList.get(0);
//        Node end = nodeList.get(30);
//
//        watchDog.snapShotFreeMemory("BeforeAlgorithm");
//        Results<Node> results = BestFirst.findShortestPath(start, end, watchDog);
//        watchDog.snapShotFreeMemory("AfterAlgorithm");
//
//        System.out.println("/////////////////////////////// BEST FIRST SEARCH RESULTS");
//        System.out.println("///////// STARTING NODE: " + start.getName());
//        System.out.println("///////// ENDING NODE: " + end.getName());
//        System.out.println("///////// PATH: ");
//        results.getPath().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
//        System.out.println("///////////////////////////////");

//    }
}
