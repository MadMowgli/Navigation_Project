package fhnw.wibb.best_first;

import fhnw.wibb.best_first.Models.Node;
import fhnw.wibb.util.Loader;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        // Load data from the csv files
        ArrayList<Node> nodeList = Loader.loadBestFirstNodes();
        System.out.println("");
    }
}
