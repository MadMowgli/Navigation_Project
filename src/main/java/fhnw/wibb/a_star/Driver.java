package fhnw.wibb.a_star;

import fhnw.wibb.a_star.Models.AStarAlgorithm;
import fhnw.wibb.a_star.Models.Node;
import fhnw.wibb.a_star.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    // This is the driver class for the a* package. Use it to run the algorithm.
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.load();
        List<Node> shortestPath = AStarAlgorithm.aStar(nodeList.get(0), nodeList.get(10));

        System.out.println();

    }

}
