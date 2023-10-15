package fhnw.wibb.a_star.util;

import fhnw.wibb.a_star.Models.Edge;
import fhnw.wibb.a_star.Models.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Loader {

    public static ArrayList<Node> load() {

        // Read edges
        String edgesFile = "/edges.csv";
        String nodesFile = "/nodes.csv";

        ArrayList<Node> nodeList = new ArrayList<>();

        // Reading edges
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(edgesFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");
                Node node1 = new Node(parts[0]);
                Node node2 = new Node(parts[1]);
                Double length = Double.parseDouble(parts[2]);
                Edge edge = new Edge(node1, node2, length);
                node1.getEdges().add(edge);
                node2.getEdges().add(edge);

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

            // Add coordinates to all nodes in the list
            try(BufferedReader brn = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(nodesFile)))) {

                // Read through all nodes in the nodes file
                String nodeLine;
                while ((nodeLine = brn.readLine()) != null) {
                    String[] parts = nodeLine.split(";");
                    Node node = new Node(parts[0]);
                    if(nodeList.contains(node)) {
                        node = nodeList.get(nodeList.indexOf(node));
                        System.out.println("");
                    }

                    node.setX(Integer.parseInt(parts[1]));
                    node.setY(Integer.parseInt(parts[2]));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return nodeList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
