package fhnw.wibb.util;

import fhnw.wibb.a_star.Models.Edge;
import fhnw.wibb.depth_first.Models.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Loader {

    // This class contains data loading functions for each of the different algorithms.
    // There are different loading functions, since every algorithm works with a slightly different representation of a node

    // Depth First loading method
    public static ArrayList<Node> loadDepthFirstNodes() {
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

    // Breadth First loading method
    public static ArrayList<fhnw.wibb.breadth_first.Models.Node> loadBreadthFirstNodes() {
        String edgesFile = "/edges.csv";

        ArrayList<fhnw.wibb.breadth_first.Models.Node> nodeList = new ArrayList<>();

        // Reading edges
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(edgesFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");
                fhnw.wibb.breadth_first.Models.Node node1 = new fhnw.wibb.breadth_first.Models.Node(parts[0]);
                fhnw.wibb.breadth_first.Models.Node node2 = new fhnw.wibb.breadth_first.Models.Node(parts[1]);

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
            Set<fhnw.wibb.breadth_first.Models.Node> nodeSet = new HashSet<>(nodeList);
            nodeList.clear();
            nodeList.addAll(nodeSet);

            return nodeList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Best First loading method
    public static ArrayList<fhnw.wibb.best_first.Models.Node> loadBestFirstNodes() {

        // Read edges
        String edgesFile = "/edges.csv";
        String nodesFile = "/nodes.csv";

        ArrayList<fhnw.wibb.best_first.Models.Node> nodeList = new ArrayList<>();

        // Reading edges
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(edgesFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                // Loop through each line, getting both nodes of an edge
                String[] parts = line.split(";");
                fhnw.wibb.best_first.Models.Node node1 = new fhnw.wibb.best_first.Models.Node(parts[0]);
                fhnw.wibb.best_first.Models.Node node2 = new fhnw.wibb.best_first.Models.Node(parts[1]);

                // Take already existing nodes to set neighbours
                if(nodeList.contains(node1)) { node1 = nodeList.get(nodeList.indexOf(node1)); }
                if(nodeList.contains(node2)) { node2 = nodeList.get(nodeList.indexOf(node2));}

                // Set neighbours & add nodes to list
                node1.getNeighbours().add(node2);
                node2.getNeighbours().add(node1);
                nodeList.add(node1);
                nodeList.add(node2);
            }

            // Remove dupes
            Set<fhnw.wibb.best_first.Models.Node> nodeSet = new HashSet<>(nodeList);
            nodeList.clear();
            nodeList.addAll(nodeSet);

            // Add coordinates to all nodes in the list
            try(BufferedReader brn = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(nodesFile)))) {

                // Read through all nodes in the nodes file
                String nodeLine;
                while ((nodeLine = brn.readLine()) != null) {

                    // Loop through each line, getting every node
                    String[] parts = nodeLine.split(";");
                    fhnw.wibb.best_first.Models.Node node = new fhnw.wibb.best_first.Models.Node(parts[0]);

                    // Getting an already existing node instead of creating a new instance
                    if(nodeList.contains(node)) { node = nodeList.get(nodeList.indexOf(node));}

                    // Setting X and Y of each node
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

    // A-Star loading method
    public static ArrayList<fhnw.wibb.a_star.Models.Node> loadAStarNodes() {

        // Read edges
        String edgesFile = "/edges.csv";
        String nodesFile = "/nodes.csv";

        ArrayList<fhnw.wibb.a_star.Models.Node> nodeList = new ArrayList<>();

        // Reading edges
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(edgesFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");
                fhnw.wibb.a_star.Models.Node node1 = new fhnw.wibb.a_star.Models.Node(parts[0]);
                fhnw.wibb.a_star.Models.Node node2 = new fhnw.wibb.a_star.Models.Node(parts[1]);
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
            Set<fhnw.wibb.a_star.Models.Node> nodeSet = new HashSet<>(nodeList);
            nodeList.clear();
            nodeList.addAll(nodeSet);

            // Add coordinates to all nodes in the list
            try(BufferedReader brn = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(nodesFile)))) {

                // Read through all nodes in the nodes file
                String nodeLine;
                while ((nodeLine = brn.readLine()) != null) {
                    String[] parts = nodeLine.split(";");
                    fhnw.wibb.a_star.Models.Node node = new fhnw.wibb.a_star.Models.Node(parts[0]);
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