package org.example.sorts;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortRandomSmallAndMedium() {
        Random rnd = new Random(123);
        int[] sizes = {0, 1, 2, 3, 10, 100, 1000};
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);

            Metrics m = new Metrics();
            MergeSort.sort(a, m);

            assertArrayEquals(expected, a, "Mismatch on size " + n);
        }
    }

    @Test
    void sortReverseSorted() {
        int n = 2000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(a, m);

        assertArrayEquals(expected, a);
    }

    @Test
    void allocationCountedOnce() {
        int n = 100;
        int[] a = new int[n];
        Random rnd = new Random(1);
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt();

        Metrics m = new Metrics();
        MergeSort.sort(a, m);

        assertEquals(1, m.getAllocations(), "aux buffer should be allocated exactly once");
    }

    @Test
    void sortsStableForDuplicates() {
        int[] a = {5, 2, 3, 2, 1, 2};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        MergeSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }
}
