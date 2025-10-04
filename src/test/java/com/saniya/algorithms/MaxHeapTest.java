package com.saniya.algorithms;

import com.saniya.metrics.PerformanceTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {
    private MaxHeap heap;
    private PerformanceTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new PerformanceTracker();
        heap = new MaxHeap(100, tracker);
    }

    @Test
    void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertThrows(IllegalStateException.class, heap::extractMax);
    }

    @Test
    void testSingleElement() {
        heap.insert(5);
        assertEquals(1, heap.getSize());
        assertEquals(5, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDuplicates() {
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        assertEquals(3, heap.extractMax());
        assertEquals(3, heap.extractMax());
        assertEquals(3, heap.extractMax());
    }

    @Test
    void testSortedInput() {
        int[] sorted = {1, 2, 3, 4, 5};
        heap.buildHeap(sorted);
        assertEquals(5, heap.extractMax());
        assertEquals(4, heap.extractMax());
    }

    @Test
    void testReverseSortedInput() {
        int[] reverse = {5, 4, 3, 2, 1};
        heap.buildHeap(reverse);
        assertEquals(5, heap.extractMax());
        assertEquals(4, heap.extractMax());
    }

    @Test
    void testIncreaseKey() {
        heap.insert(1);
        heap.insert(2);
        heap.increaseKey(0, 10);
        assertEquals(10, heap.extractMax());
    }

    @Test
    void testRandomInputs() {
        Random rand = new Random();
        for (int test = 0; test < 100; test++) {
            heap = new MaxHeap(10, tracker);
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for (int i = 0; i < 10; i++) {
                int val = rand.nextInt(100);
                heap.insert(val);
                pq.add(val);
            }
            while (!pq.isEmpty()) {
                assertEquals(pq.poll(), heap.extractMax());
            }
        }
    }

    @Test
    void testCrossValidation() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int[] arr = {3, 1, 4, 1, 5};
        for (int val : arr) {
            heap.insert(val);
            pq.add(val);
        }
        while (!pq.isEmpty()) {
            assertEquals(pq.poll(), heap.extractMax());
        }
    }
}