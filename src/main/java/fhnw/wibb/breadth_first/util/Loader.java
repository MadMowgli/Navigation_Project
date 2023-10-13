package fhnw.wibb.breadth_first.util;

import fhnw.wibb.breadth_first.Models.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Loader {

    public static ArrayList<Node> load() {
        String edgesFile = "/edges.csv";

        ArrayList<Node> nodeList = new ArrayList<>();

        // Reading edges
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(edgesFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");
                Node node1 = new Node(parts[0]);
                Node node2 = new Node(parts[1]);

                if(nodeList.contains(node1)) {
                    node1 = nodeList.get(nodeList.indexOf(node1));
                }
                if(nodeList.contains(node2)) {
                    node2 = nodeList.get(nodeList.indexOf(node2));
                }

                node1.getNeighbours().add(node2);
                node2.getNeighbours().add(node1);
                nodeList.add(node1);
                nodeList.add(node2);
            }

            // Remove dupes
            Set<Node> nodeSet = new HashSet<>(nodeList);
            nodeList.clear();
            nodeList.addAll(nodeSet);

            return nodeList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
