package fhnw.wibb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WatchDog {

    // Fields
    private final HashMap<String, HashMap<String, Long>> measurements;
    private final ArrayList<String> acceptedFormats;

    // Constructor
    public WatchDog() {
        this.measurements = new HashMap<>();
        this.acceptedFormats = new ArrayList<>(Arrays.asList("ns", "ms", "s"));
    }


    // Methods
    // This method "starts" a new timer with the given name
    public void startMeasurement(String name) {
        measurements.put(name, new HashMap<>());
        measurements.get(name).put("start", System.nanoTime());
    }

    // This method "stops" an existing timer and returns (end - start), which is the measured time in nanoseconds
    public long stopMeasurement(String name) {
        if(!measurements.containsKey(name)) { throw new RuntimeException("No Measurement found with key: " + name); }
        measurements.get(name).put("stop", System.nanoTime());
        measurements.get(name).put("total", measurements.get(name).get("stop") - measurements.get(name).get("start"));
        return measurements.get(name).get("total");
    }

    public double getMeasurement(String name, String format) {
        if(!measurements.containsKey(name)) { throw new RuntimeException("No Measurement found with key: " + name); }
        if(!acceptedFormats.contains(format)) { throw new IllegalArgumentException("Time format '"+format+"' not allowed! Use ns, ms or s"); }
        return switch (format) {
            case "ns" -> measurements.get(name).get("total");
            case "ms" -> measurements.get(name).get("total") / 1000000.0;
            case "s" -> measurements.get(name).get("total") / 1000000000.0;
            default -> -1;
        };
    }

}
