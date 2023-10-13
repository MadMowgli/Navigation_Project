package fhnw.wibb.breadth_first;

import fhnw.wibb.breadth_first.Models.BreadthFirst;
import fhnw.wibb.breadth_first.Models.Node;
import fhnw.wibb.breadth_first.util.Loader;

import java.util.ArrayList;

public class Driver {

    // This is the driver class for the breadth first package. Use it to run the algorithm.
    public static void main(String[] args) {

        ArrayList<Node> nodeList = Loader.load();
        BreadthFirst.breadthFirst(nodeList.get(0), nodeList.get(20));
        System.out.println();

    }

}
