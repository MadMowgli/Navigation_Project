package fhnw.wibb.depth_first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

                node1.getNodeNeighbours().add(node2);
                node2.getNodeNeighbours().add(node1);
                nodeList.add(node1);
                nodeList.add(node2);
            }
            return nodeList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
