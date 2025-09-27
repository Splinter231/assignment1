package org.example.bench;

import org.example.metrics.CSVWriter;
import org.example.metrics.Metrics;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.function.BiConsumer;

public class BenchmarkRunner {

    public static void runSort(String algorithmName,
                               BiConsumer<int[], Metrics> sortFunc,
                               int n, int trials, long seed, Path csvPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(csvPath)) {
            writer.writeHeader();
            Random rnd = new Random(seed);

            // warm-up
            int[] warm = genRandomArray(n, rnd.nextLong());
            sortFunc.accept(warm, new Metrics());

            for (int t = 0; t < trials; t++) {
                int[] arr = genRandomArray(n, rnd.nextLong());
                Metrics metrics = new Metrics();
                long start = System.nanoTime();
                sortFunc.accept(arr, metrics);
                long timeNs = System.nanoTime() - start;
                writer.writeRow(algorithmName, n, t, timeNs,
                        metrics.getMaxDepth(),
                        metrics.getComparisons(),
                        metrics.getSwaps(),
                        metrics.getAllocations(),
                        seed + t);
            }
        }
    }

    private static int[] genRandomArray(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }
}
