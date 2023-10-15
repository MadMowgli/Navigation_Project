package fhnw.wibb.best_first;

import fhnw.wibb.best_first.Models.BestFirst;
import fhnw.wibb.best_first.Models.Node;
import fhnw.wibb.util.Loader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadBestFirstNodes();
        Node start = nodeList.get(0);
        Node destination = nodeList.get(10);

        ArrayList<Node> shortestPath = BestFirst.findShortestPath(start, destination);
        System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + destination.getName());
        System.out.println("///////// PATH: ");
        shortestPath.stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

    }
}
