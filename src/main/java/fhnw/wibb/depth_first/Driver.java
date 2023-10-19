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

        ArrayList<Node> nodeList = Loader.loadDepthFirstNodes();
        Node start = nodeList.get(27);
        Node end = nodeList.get(98);

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
