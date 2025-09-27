package org.example.bench;
import java.io.IOException;
import java.nio.file.Paths;

public class RunBench {
    public static void main(String[] args) throws IOException {
        BenchmarkRunner.runSort("arraysort", (arr, metrics) -> java.util.Arrays.sort(arr),
                1000, 3, 42L, Paths.get("bench-results.csv"));
        System.out.println("Done. CSV: bench-results.csv");
    }
}
