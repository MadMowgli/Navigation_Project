package fhnw.wibb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WatchDog {

    // Fields
    private final HashMap<String, HashMap<String, Long>> measurements;
    private final ArrayList<String> acceptedFormats;
    private final String algorithmName;

    // Constructor
    public WatchDog(String algorithmName) {
        this.algorithmName = algorithmName;
        this.measurements = new HashMap<>();
        measurements.put(algorithmName, new HashMap<>());
        this.acceptedFormats = new ArrayList<>(Arrays.asList("ns", "ms", "s"));
    }


    // Methods
    // This method "starts" a new timer with the given algorithmName
    public void startTime() {
        measurements.get(algorithmName).put("start", System.nanoTime());
    }

    // This method "stops" an existing timer and returns (end - start), which is the measured time in nanoseconds
    public long stopTime() {
        if(!measurements.containsKey(algorithmName)) { throw new RuntimeException("No Measurement found with key: " + algorithmName); }
        measurements.get(algorithmName).put("stop", System.nanoTime());
        measurements.get(algorithmName).put("total", measurements.get(algorithmName).get("stop") - measurements.get(algorithmName).get("start"));
        return measurements.get(algorithmName).get("total");
    }

    public void snapShotFreeMemory() {
        String measurementName = "FreeMemory_" + System.currentTimeMillis();
        measurements.get(algorithmName).put(measurementName, Runtime.getRuntime().freeMemory());
    }

    public void snapShotTotalMemory() {
        String measurementName = "TotalMemory" + System.currentTimeMillis();
        measurements.get(algorithmName).put(measurementName, Runtime.getRuntime().totalMemory());
    }


    public double getMeasurement(String format) {
        if(!measurements.containsKey(algorithmName)) { throw new RuntimeException("No Measurement found with key: " + algorithmName); }
        if(!acceptedFormats.contains(format)) { throw new IllegalArgumentException("Time format '"+format+"' not allowed! Use ns, ms or s"); }
        return switch (format) {
            case "ns" -> measurements.get(algorithmName).get("total");
            case "ms" -> measurements.get(algorithmName).get("total") / 1000000.0;
            case "s" -> measurements.get(algorithmName).get("total") / 1000000000.0;
            default -> -1;
        };
    }

}
