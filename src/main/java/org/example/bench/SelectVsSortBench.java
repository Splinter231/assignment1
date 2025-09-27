package org.example.bench;

import org.example.select.DeterministicSelect;
import org.example.sorts.QuickSort;
import org.example.sorts.MergeSort;
import org.example.metrics.Metrics;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBench {

    @Param({"1000", "10000", "100000"})
    private int n;

    private int[] data;
    private Random random;

    @Setup(Level.Invocation)
    public void setup() {
        random = new Random(42);
        data = random.ints(n, 0, 1_000_000).toArray();
    }

    @Benchmark
    public int deterministicSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        int k = n / 2;
        return DeterministicSelect.select(copy, k);
    }

    @Benchmark
    public void quickSort() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        QuickSort.sort(copy, m);
    }

    @Benchmark
    public void mergeSort() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        MergeSort.sort(copy, m);
    }
}
