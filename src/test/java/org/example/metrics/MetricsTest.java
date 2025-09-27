package org.example.metrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {
    @Test
    void depthTracking() {
        Metrics m = new Metrics();
        assertEquals(0, m.getMaxDepth());
        m.enter();
        assertEquals(1, m.getMaxDepth());
        m.enter();
        assertEquals(2, m.getMaxDepth());
        m.exit();
        m.exit();
        assertEquals(2, m.getMaxDepth());
    }

    @Test
    void counters() {
        Metrics m = new Metrics();
        assertEquals(0, m.getComparisons());
        m.incComparisons();
        m.incComparisons();
        assertEquals(2, m.getComparisons());
    }
}
