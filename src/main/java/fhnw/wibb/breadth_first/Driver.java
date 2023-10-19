package fhnw.wibb.breadth_first;

import fhnw.wibb.breadth_first.Models.BreadthFirst;
import fhnw.wibb.breadth_first.Models.Node;
import fhnw.wibb.util.Loader;
import fhnw.wibb.util.Results;
import fhnw.wibb.util.WatchDog;

import java.util.ArrayList;

public class Driver {

    // This is the driver class for the breadth first package. Use it to run the algorithm.
    public static void main(String[] args) {

        /*
        // Create a watchdog object to time things
        WatchDog watchDog = new WatchDog("BreadthFirst");
        watchDog.snapShotTotalMemory();     // See how much  memory we have at total

        // Load data from the csv files
        watchDog.snapShotFreeMemory("BeforeLoadingData");
        ArrayList<Node> nodeList = Loader.loadBreadthFirstNodes();
        watchDog.snapShotFreeMemory("AfterLoadingData");

        for(int i = 1; i < 10; i++) {

            Node start = nodeList.get(i);
            Node end = nodeList.get(nodeList.size() - i);

            try {
                System.out.println("Start: " + start.getName());
                System.out.println("End: " + end.getName());

                // Search the shortest path using a*
                watchDog.snapShotFreeMemory("BeforeAlgorithm");
                Results<Node> results = BreadthFirst.breadthFirstFind(start, end, watchDog);
                watchDog.snapShotFreeMemory("AfterAlgorithm");

                results.writeToCSV("Results_BreadthFirst.csv");
            } catch (Exception e) {
                System.out.println("No path found between: " + start.getName() + " and " + end.getName());
            }

        }


    } */

    // Create a watchdog object to time things
    WatchDog watchDog = new WatchDog("BreadthFirst");
    watchDog.snapShotTotalMemory(); // See how much  memory we have at total

    // Load data from the csv files
    watchDog.snapShotFreeMemory("BeforeLoadingData");
    ArrayList<Node> nodeList = Loader.loadBreadthFirstNodes();
    watchDog.snapShotFreeMemory("AfterLoadingData");

    Node start = nodeList.get(0);
    Node end = nodeList.get(30);

    //Search the shortest path using breadth first
    Results<Node> results = BreadthFirst.breadthFirstFind(start, end, watchDog);
    System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
      System.out.println("///////// STARTING NODE: "+start.getName());
      System.out.println("///////// ENDING NODE: "+end.getName());
      System.out.println("///////// PATH: ");
      assert results !=null;
      results.getPath().

    forEach(node ->System.out.println("//// "+node.getName()+" ->"));
      System.out.println("///////////////////////////////");

    }

}
