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

        // Create a watchdog object to time things
        WatchDog watchDog = new WatchDog("AStar");
        watchDog.snapShotTotalMemory();     // See how much  memory we have at total

        // Load data from the csv files
        watchDog.snapShotFreeMemory("BeforeLoadingData");
        ArrayList<Node> nodeList = Loader.loadAStarNodes();
        watchDog.snapShotFreeMemory("AfterLoadingData");

        for (int i = 1; i < 10; i++) {

            Node start = nodeList.get(i);
            Node end = nodeList.get(nodeList.size() - i);
            try {
                // Search the shortest path using a*
                watchDog.snapShotFreeMemory("BeforeAlgorithm");
                Results<Node> results = AStar.aStarSearch(start, end, watchDog);
                watchDog.snapShotFreeMemory("AfterAlgorithm");

                results.writeToCSV("AStar.csv");
            } catch (Exception e) {
                System.out.println("No path found between: " + start.getName() + " and " + end.getName());
            }

        }


    }

}
