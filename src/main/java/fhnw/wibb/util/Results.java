package fhnw.wibb.util;

import java.util.ArrayList;

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

}
