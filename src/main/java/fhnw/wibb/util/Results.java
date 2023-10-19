package fhnw.wibb.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Results<T extends iNode> {

    // Fields
    private ArrayList<T> path;
    private WatchDog measurements;


    // Constructor
    public Results(ArrayList<T> path, WatchDog measurements) {
        this.path = path;
        this.measurements = measurements;
    }


    // Getters & Setters

    public ArrayList<T> getPath() {
        return path;
    }

    public WatchDog getMeasurements() {
        return measurements;
    }

    public void writeToCSV(String fileName) {

        String filePath = Results.class.getClassLoader().getResource("").getPath() + fileName;

        try(FileWriter fileWriter = new FileWriter(filePath, true)) {

            // CSV String format: AlgorithmName;StartNode;EndNode;Time;
            String csvString = measurements.getAlgorithmName() + ";"
                    + path.get(0).getName() + ";"
                    + path.get(path.size() - 1).getName() + ";"
                    + measurements.getAllMeasurements().get("Time_Total")
                    + "\n";
            fileWriter.write(csvString);

            // Close the fw
            fileWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
