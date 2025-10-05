package com.saniya.algorithms;

import com.saniya.metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class MaxHeapBenchmark {
    @Param({"100", "1000", "10000", "100000"})
    private int size;

    @Benchmark
    public void benchmarkBuildAndExtract() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(size, tracker);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        heap.buildHeap(arr);
        for (int i = 0; i < size; i++) heap.extractMax();
    }
}