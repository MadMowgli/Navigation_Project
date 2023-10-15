package fhnw.wibb.a_star;

import fhnw.wibb.a_star.Models.AStar;
import fhnw.wibb.a_star.Models.Node;
import fhnw.wibb.breadth_first.Models.BreadthFirst;
import fhnw.wibb.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    // This is the driver class for the a* package. Use it to run the algorithm.
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadAStarNodes();
        Node start = nodeList.get(0);
        Node end = nodeList.get(10);

        // Search the shortest path using a*
        List<Node> shortestPath = AStar.aStarSearch(start, end);
        System.out.println("/////////////////////////////// BREADTH FIRST SEARCH RESULTS");
        System.out.println("///////// STARTING NODE: " + start.getName());
        System.out.println("///////// ENDING NODE: " + end.getName());
        System.out.println("///////// PATH: ");
        shortestPath.stream().forEach(node -> System.out.println("//// " + node.getName() + " ->"));
        System.out.println("///////////////////////////////");

        System.out.println();

    }

}
