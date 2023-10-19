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


        for (int i = 1; i < 100; i++) {
            {
                Node start = nodeList.get(i);
                Node end = nodeList.get(nodeList.size() - i);

                try {
                    watchDog.snapShotFreeMemory("BeforeAlgorithm");
                    Results<Node> results = BestFirst.findShortestPath(start, end, watchDog);
                    watchDog.snapShotFreeMemory("AfterAlgorithm");
                    results.writeToCSV("BestFirst.csv");
                } catch (Exception e) {
                    System.out.println("No path found between: " + start.getName() + " and " + end.getName());
                }

            }
        }


    }
}
