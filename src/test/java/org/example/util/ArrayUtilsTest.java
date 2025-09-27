package org.example.util;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    @Test
    void swapWorks() {
        int[] a = {1, 2, 3};
        Metrics m = new Metrics();
        ArrayUtils.swap(a, 0, 2, m);
        assertArrayEquals(new int[]{3, 2, 1}, a);
        assertEquals(1, m.getSwaps());
    }

    @Test
    void shuffleChangesOrder() {
        int[] a = {1, 2, 3, 4, 5};
        int[] copy = a.clone();
        ArrayUtils.shuffle(a);
        assertEquals(5, a.length);
        assertTrue(a[0] != copy[0] || a[1] != copy[1] || a[2] != copy[2] || a[3] != copy[3] || a[4] != copy[4],
                "Shuffle should likely change order");
    }

    @Test
    void partitionSeparatesByPivot() {
        int[] a = {9, 3, 5, 1, 7};
        Metrics m = new Metrics();
        int pivotIndex = ArrayUtils.partition(a, 0, a.length - 1, 2, m);
        int pivotValue = a[pivotIndex];
        for (int i = 0; i < pivotIndex; i++) {
            assertTrue(a[i] <= pivotValue);
        }
        for (int i = pivotIndex + 1; i < a.length; i++) {
            assertTrue(a[i] >= pivotValue);
        }
    }

    @Test
    void isSortedWorks() {
        assertTrue(ArrayUtils.isSorted(new int[]{1, 2, 3, 4}));
        assertFalse(ArrayUtils.isSorted(new int[]{3, 1, 2}));
    }
}
