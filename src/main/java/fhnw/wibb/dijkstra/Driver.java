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

        for(int i = 1; i < 60; i++) {

            Node start = nodeList.get(i);
            Node end = nodeList.get(nodeList.size() - i);

            try {
                System.out.println(start.getName());
                System.out.println(end.getName());

                watchDog.snapShotFreeMemory("BeforeAlgorithm");
                Results<Node> results = DijkstraSearch.findShortestPath(start, end, watchDog);
                watchDog.snapShotFreeMemory("AfterAlgorithm");

                results.writeToCSV("Dijkstra.csv");
            } catch (Exception e) {
                System.out.println("No path found between: " + start.getName() + " and " + end.getName());
            }

        }

    }
}



