package com.saniya.algorithms;

import com.saniya.metrics.PerformanceTracker;

public class MaxHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MaxHeap(int capacity, PerformanceTracker tracker) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.heap = new int[capacity];
        this.size = 0;
        this.tracker = tracker;
        tracker.incrementMemoryAllocations(capacity * Integer.BYTES);
    }

    public void buildHeap(int[] array) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        heap = array;
        size = array.length;
        tracker.incrementMemoryAllocations(array.length * Integer.BYTES);
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public void insert(int key) {
        if (size == heap.length) throw new ArrayIndexOutOfBoundsException("Heap overflow");
        heap[size] = key;
        tracker.incrementArrayAccesses();
        int i = size++;
        while (i > 0) {
            int parent = (i - 1) / 2;
            tracker.incrementComparisons();
            tracker.incrementArrayAccesses(2);
            if (heap[parent] >= heap[i]) break;
            swap(parent, i);
            i = parent;
        }
    }

    public int extractMax() {
        if (size == 0) throw new IllegalStateException("Heap underflow");
        int max = heap[0];
        tracker.incrementArrayAccesses();
        heap[0] = heap[--size];
        heapify(0);
        return max;
    }

    public void increaseKey(int i, int newKey) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException("Invalid index");
        tracker.incrementArrayAccesses();
        if (newKey < heap[i]) throw new IllegalArgumentException("New key must be larger");
        heap[i] = newKey;
        while (i > 0) {
            int parent = (i - 1) / 2;
            tracker.incrementComparisons();
            tracker.incrementArrayAccesses(2);
            if (heap[parent] >= heap[i]) break;
            swap(parent, i);
            i = parent;
        }
    }

    private void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        tracker.incrementArrayAccesses(3);
        if (left < size) {
            tracker.incrementComparisons();
            if (heap[left] > heap[largest]) largest = left;
        }
        if (right < size) {
            tracker.incrementComparisons();
            if (heap[right] > heap[largest]) largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        tracker.incrementSwaps();
        tracker.incrementArrayAccesses(4);
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
}