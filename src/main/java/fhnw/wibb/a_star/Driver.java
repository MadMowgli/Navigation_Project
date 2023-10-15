package fhnw.wibb.a_star;

import fhnw.wibb.a_star.Models.AStar;
import fhnw.wibb.a_star.Models.Node;
import fhnw.wibb.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    // This is the driver class for the a* package. Use it to run the algorithm.
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadAStarNodes();
        List<Node> shortestPath = AStar.aStarSearch(nodeList.get(0), nodeList.get(10));

        System.out.println();

    }

}
