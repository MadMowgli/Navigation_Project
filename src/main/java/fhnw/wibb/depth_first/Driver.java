package fhnw.wibb.depth_first;
import fhnw.wibb.depth_first.Models.DepthFirstSearch;
import fhnw.wibb.depth_first.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.ArrayList;
public class Driver {

    // This is the driver class for the depth  first package. Use it to run the algorithm.
    public static void main(String[] args) {

        /*
        WatchDog watchDog = new WatchDog("DepthFirst");
        watchDog.snapShotTotalMemory();     // See how much  memory we have at total

        watchDog.snapShotFreeMemory("BeforeLoadingData");
        ArrayList<Node> nodeList = Loader.loadDepthFirstNodes();
        watchDog.snapShotFreeMemory("AfterLoadingData");


        for(int i = 1; i < 10; i++) {

            Node start = nodeList.get(i);
            Node end = nodeList.get(nodeList.size() - i);

            try {

                watchDog.snapShotFreeMemory("BeforeAlgorithm");
                Results<Node> results = DepthFirstSearch.findPath(start, end, watchDog);
                watchDog.snapShotFreeMemory("AfterAlgorithm");

                results.writeToCSV("Results_DepthFirst.csv");
            } catch (Exception e) {
                System.out.println("No path found between: " + start.getName() + " and " + end.getName());
            }

        }

    } */

        ArrayList<Node> nodeList = Loader.loadDepthFirstNodes();
        Node start = nodeList.get(0);
        Node end = nodeList.get(30);

        WatchDog watchDog = new WatchDog("DepthFirst");
        Results<Node> results = DepthFirstSearch.findPath(start, end, watchDog);

        System.out.println("/////////////////////////////// DEPTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + end.getName());
        System.out.println("///////// PATH: ");
        results.getPath().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

        System.out.println();

    }

}
