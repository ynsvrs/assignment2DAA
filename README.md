# **Assignment 2: Max-Heap Implementation**

## **Overview**
This project implements a **Max-Heap** data structure in **Java 23**, supporting key operations such as **increase-key** and **extract-max**.  
The implementation is **array-based** for memory efficiency and includes **comprehensive performance benchmarks** to validate empirical performance across various input types and sizes.

## -- **The project provides:**
- **CLI Benchmarks:** Measure time and memory usage for different input types (random, sorted, reverse, nearly) and sizes (100, 1000, 10000, 100000).
- **JMH Microbenchmarks:** Provide precise performance measurements for random inputs.
- **Performance Plot:** Visualizes time vs. input size on a logarithmic scale.

---

## **Project Structure**
- **Source Code:** `src/main/java/com/saniya/algorithms/MaxHeap.java` *(core max-heap implementation)*
- **Unit Tests:** `src/test/java/com/saniya/algorithms/MaxHeapTest.java` *(JUnit tests for correctness)*
- **JMH Benchmarks:** `src/test/java/com/saniya/algorithms/MaxHeapBenchmark.java` *(microbenchmarks for random inputs)*
- **CLI Benchmarks:** Generated via `BenchmarkRunner.java` *(outputs `docs/performance-plots/benchmark_all.csv`)*

## Complexity Analysis
- **Build Heap:** Θ(n)
- **Insert:** O(log n) worst/average, Ω(1) best
- **ExtractMax:** O(log n) worst/average, Ω(1) best
- **IncreaseKey:** O(log n) worst/average, Ω(1) best
- **Space Complexity:** Θ(n) for array, in-place operations

### **Performance Results**
- **JMH Results:** `docs/performance-plots/jmh_results.xlsx` *(Excel file with microbenchmark data)*
- **CLI Results:** `docs/performance-plots/benchmark_all.csv` *(CSV with time and memory data)*


---

## **Setup Instructions**

## -- **Prerequisites**
- **Java 23**
- **Maven** *(for building and running benchmarks)*
- **Python 3.x** *(for generating plots)*
- **Git**

---

## -- Install Python Dependencies (for plotting)
```bash
pip install pandas matplotlib
```

## Build project
```bash
mvn clean package 
``` 

## Run CLI Benchmarks
Generate performance data for input sizes (100, 1000, 10000, 100000) 
and types (random, sorted, reverse, nearly).
- **All Cases (outputs docs/performance-plots/benchmark_all.csv):**
```bash
java -jar target/maxheap-1.0-SNAPSHOT.jar all
```
This will create `docs/performance-plots/benchmark_all.csv`.
- **Single Case (e.g., size 100, random input):**
```bash
java -jar target/maxheap-1.0-SNAPSHOT.jar 100 random
```

## Run Unit Tests
- Execute JUnit tests to verify implementation correctness:
```bash
mvn test
```
## Run JMH Benchmarks
- Measure precise performance for random inputs across sizes (100, 1000, 10000, 100000):
``` bash
mvn clean package
java -jar target/jmh-benchmarks.jar com.saniya.algorithms.MaxHeapBenchmark
```
- Copy the output to docs/performance-plots/jmh_results.xlsx using Excel.


## Input Types and Sizes
- Input Types: random, sorted, reverse, nearly
- Sizes: 100, 1000, 10000, 100000

## Performance results
- JMH Benchmarks (jmh_results.xlsx): Precise measurements for random inputs, showing average time per operation (ms/op).
- CLI Benchmarks (benchmark_all.csv): Time and memory usage for all input types and sizes.
- Plot (time_vs_size.png): Visualizes time vs. input size on a log scale, comparing input types.
- **For detailed results, see docs/performance-plots/.**

