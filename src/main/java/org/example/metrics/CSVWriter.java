package org.example.metrics;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;

public final class CSVWriter implements Closeable {
    private final BufferedWriter bw;
    private final Path path;
    private boolean headerWritten = false;

    public CSVWriter(Path path) throws IOException {
        this.path = path;
        Path parent = path.getParent() == null ? Paths.get(".") : path.getParent();
        Files.createDirectories(parent);
        this.bw = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public synchronized void writeHeader() throws IOException {
        if (!headerWritten) {
            if (Files.size(path) == 0) {
                bw.write("algorithm,n,trial,time_ns,max_depth,comparisons,swaps,allocations,seed,timestamp");
                bw.newLine();
                bw.flush();
            }
            headerWritten = true;
        }
    }

    public synchronized void writeRow(String algorithm,
                                      int n,
                                      int trial,
                                      long timeNs,
                                      int maxDepth,
                                      long comparisons,
                                      long swaps,
                                      long allocations,
                                      long seed) throws IOException {
        String ts = Instant.now().toString();
        String row = String.format("%s,%d,%d,%d,%d,%d,%d,%d,%d,%s",
                algorithm, n, trial, timeNs, maxDepth, comparisons, swaps, allocations, seed, ts);
        bw.write(row);
        bw.newLine();
        bw.flush();
    }

    public static void write(String filePath,
                             String algorithm,
                             int n,
                             long comparisons,
                             long swaps,
                             long allocations,
                             int maxDepth,
                             long timeNs) {
        Path p = Paths.get(filePath);
        long seed = System.currentTimeMillis();
        try (CSVWriter writer = new CSVWriter(p)) {
            writer.writeHeader();
            writer.writeRow(algorithm, n, 1, timeNs, maxDepth, comparisons, swaps, allocations, seed);
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        bw.close();
    }
}
