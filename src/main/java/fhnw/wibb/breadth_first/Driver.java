package fhnw.wibb.breadth_first;

import fhnw.wibb.breadth_first.Models.BreadthFirst;
import fhnw.wibb.breadth_first.Models.Node;
import fhnw.wibb.util.Loader;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

    // This is the driver class for the breadth first package. Use it to run the algorithm.
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadBreadthFirstNodes();

        // Pick 2 random nodes for start & end
        Node start = nodeList.get(ThreadLocalRandom.current().nextInt(0, nodeList.size() + 1));
        Node end = nodeList.get(ThreadLocalRandom.current().nextInt(0, nodeList.size() + 1));

        // Search the shortest path using breadth first
        ArrayList<Node> shortestPath = BreadthFirst.breadthFirstFind(start, end);
        System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + end.getName());
        System.out.println("///////// PATH: ");
        shortestPath.stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

        // TODO: ADD TIMERS
        // TODO: ADD DOCUMENTATION
    }

}
