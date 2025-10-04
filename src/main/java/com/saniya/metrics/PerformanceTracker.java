package com.saniya.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long memoryAllocations = 0;
    private final Runtime runtime = Runtime.getRuntime();

    public void incrementComparisons() { comparisons++; }
    public void incrementSwaps() { swaps++; }
    public void incrementArrayAccesses() { arrayAccesses++; }
    public void incrementArrayAccesses(long count) { arrayAccesses += count; }
    public void incrementMemoryAllocations(long bytes) { memoryAllocations += bytes; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getMemoryAllocations() { return memoryAllocations; }
    public long getUsedMemory() { return runtime.totalMemory() - runtime.freeMemory(); }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
    }

    public String toCSV() {
        return "comparisons," + comparisons + "\n" +
                "swaps," + swaps + "\n" +
                "arrayAccesses," + arrayAccesses + "\n" +
                "memoryAllocations," + memoryAllocations + "\n" +
                "usedMemory," + getUsedMemory() + "\n";
    }

    public void exportToCSV(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(toCSV());
        }
    }
}