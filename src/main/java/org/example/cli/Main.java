package org.example.cli;

import org.example.metrics.CSVWriter;
import org.example.metrics.Metrics;
import org.example.sorts.MergeSort;
import org.example.sorts.QuickSort;
import org.example.select.DeterministicSelect;
import org.example.closest.ClosestPair;

import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar app.jar <algorithm> <n>");
            System.out.println("Algorithms: mergesort, quicksort, select, closest");
            return;
        }

        String algo = args[0];
        int n = Integer.parseInt(args[1]);

        Random random = new Random();
        int[] arr = random.ints(n, 0, 1_000_000).toArray();

        Metrics metrics = new Metrics();

        switch (algo.toLowerCase()) {
            case "mergesort" -> MergeSort.sort(arr, metrics);
            case "quicksort" -> QuickSort.sort(arr, metrics);
            case "select" -> {
                int k = n / 2;
                DeterministicSelect.select(arr, k);
            }
            case "closest" -> {
                ClosestPair.Point[] points = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    points[i] = new ClosestPair.Point(random.nextDouble(), random.nextDouble());
                }
                ClosestPair.findClosest(points);
            }
            default -> {
                System.out.println("Unknown algorithm: " + algo);
                return;
            }
        }

        try (CSVWriter writer = new CSVWriter(Paths.get("bench-results.csv"))) {
            writer.writeHeader();
            writer.writeRow(
                    algo, n, 1,                      // algo, n, trial
                    System.nanoTime(),               // timeNs
                    metrics.getMaxDepth(),           // max depth
                    metrics.getComparisons(),        // comparisons
                    metrics.getSwaps(),              // swaps
                    metrics.getAllocations(),        // allocations
                    0L                               // seed
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done. Results written to bench-results.csv");
    }
}
