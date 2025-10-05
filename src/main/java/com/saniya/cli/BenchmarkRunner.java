package com.saniya.cli;

import com.saniya.algorithms.MaxHeap;
import com.saniya.metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {
    private static final int[] SIZES = {100, 1000, 10000, 100000};
    private static final String[] TYPES = {"random", "sorted", "reverse", "nearly"};
    private static final String CSV_FILE = "benchmark_all.csv";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar maxheap.jar <size|all> [type]");
            return;
        }

        initializeCSV();
        if (args[0].equals("all")) {
            runAllBenchmarks();
        } else {
            int size = Integer.parseInt(args[0]);
            String type = args.length > 1 ? args[1] : "random";
            runBenchmark(size, type);
        }
    }

    private static void initializeCSV() {
        try (FileWriter writer = new FileWriter(CSV_FILE, false)) {
            writer.write("size,type,time_ms,comparisons,swaps,arrayAccesses,memoryAllocations,usedMemory\n");
        } catch (IOException e) {
            System.err.println("Failed to initialize CSV: " + e.getMessage());
        }
    }

    private static void runAllBenchmarks() {
        for (int size : SIZES) {
            for (String type : TYPES) {
                runBenchmark(size, type);
            }
        }
    }

    private static void runBenchmark(int size, String type) {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(size * 2, tracker);

        long startTime = System.nanoTime();
        int[] input = generateInput(size, type);
        heap.buildHeap(input);
        for (int i = 0; i < size / 2; i++) {
            heap.increaseKey(i % heap.getSize(), input[i % size] + 1);
            heap.extractMax();
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Size: %d, Type: %s, Time: %.3f ms%n", size, type, timeMs);
        System.out.println(tracker.toCSV());
        appendToCSV(size, type, timeMs, tracker);
    }

    private static void appendToCSV(int size, String type, double timeMs, PerformanceTracker tracker) {
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            writer.write(String.format("%d,%s,%.3f,%d,%d,%d,%d,%d%n",
                    size, type, timeMs, tracker.getComparisons(), tracker.getSwaps(),
                    tracker.getArrayAccesses(), tracker.getMemoryAllocations(), tracker.getUsedMemory()));
        } catch (IOException e) {
            System.err.println("CSV append failed: " + e.getMessage());
        }
    }

    private static int[] generateInput(int n, String type) {
        int[] arr = new int[n];
        Random rand = new Random();
        switch (type) {
            case "random":
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt();
                break;
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) arr[i] = i;
                if (n > 0) arr[0] = n;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        return arr;
    }
}