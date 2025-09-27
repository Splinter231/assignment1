package org.example.metrics;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class Metrics {
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong swaps = new AtomicLong(0);
    private final AtomicLong allocations = new AtomicLong(0);

    private final ThreadLocal<Integer> currentDepth = ThreadLocal.withInitial(() -> 0);
    private final AtomicInteger maxDepth = new AtomicInteger(0);

    public void enter() {
        int d = currentDepth.get() + 1;
        currentDepth.set(d);
        maxDepth.getAndUpdate(prev -> Math.max(prev, d));
    }

    public void exit() {
        int d = currentDepth.get() - 1;
        if (d < 0) d = 0;
        currentDepth.set(d);
    }

    public void incComparisons() { comparisons.incrementAndGet(); }
    public void addComparisons(long n) { comparisons.addAndGet(n); }

    public void incSwaps() { swaps.incrementAndGet(); }
    public void addSwaps(long n) { swaps.addAndGet(n); }

    public void addAllocations(long n) { allocations.addAndGet(n); }

    public long getComparisons() { return comparisons.get(); }
    public long getSwaps() { return swaps.get(); }
    public long getAllocations() { return allocations.get(); }
    public int getMaxDepth() { return maxDepth.get(); }

    public void reset() {
        comparisons.set(0);
        swaps.set(0);
        allocations.set(0);
        currentDepth.set(0);
        maxDepth.set(0);
    }
    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + getComparisons() +
                ", swaps=" + getSwaps() +
                ", allocations=" + getAllocations() +
                ", maxDepth=" + getMaxDepth() +
                '}';
    }
}
