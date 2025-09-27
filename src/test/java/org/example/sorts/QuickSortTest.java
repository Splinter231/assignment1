package org.example.sorts;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void sortRandomSmallAndMedium() {
        Random rnd = new Random(42);
        int[] sizes = {0, 1, 2, 5, 50, 500};
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);

            Metrics m = new Metrics();
            QuickSort.sort(a, m);

            assertArrayEquals(expected, a, "Mismatch on size " + n);
        }
    }

    @Test
    void sortAlreadySorted() {
        int[] a = {1, 2, 3, 4, 5};
        int[] expected = Arrays.copyOf(a, a.length);
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @Test
    void sortReverseSorted() {
        int[] a = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @Test
    void stableWithDuplicates() {
        int[] a = {7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }
}
