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
            fileWriter.append("Measurement;Value\n"); // Write the header column
            fileWriter.flush();

            // Loop over each measurement taken in the watchdog and write it to the csv file
            measurements.getAllMeasurements().forEach((key, value) -> {
                try {
                    fileWriter.append(key + ";" + value + "\n");
                    fileWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Close the fw
            fileWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
