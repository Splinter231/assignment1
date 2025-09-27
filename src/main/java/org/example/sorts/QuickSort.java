package org.example.sorts;

import org.example.metrics.Metrics;
import java.util.Objects;
import java.util.Random;

public final class QuickSort {
    private static final Random rnd = new Random();

    private QuickSort() {}

    public static void sort(int[] a, Metrics metrics) {
        Objects.requireNonNull(a);
        if (a.length <= 1) return;
        quicksort(a, 0, a.length - 1, metrics);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics metrics) {
        if (lo >= hi) return;
        if (metrics != null) metrics.enter();
        try {

            int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
            swap(a, lo, pivotIndex, metrics);

            int pivot = a[lo];
            int i = lo + 1, j = hi;
            while (i <= j) {
                while (i <= hi) {
                    if (metrics != null) metrics.incComparisons();
                    if (a[i] < pivot) i++;
                    else break;
                }
                while (j > lo) {
                    if (metrics != null) metrics.incComparisons();
                    if (a[j] > pivot) j--;
                    else break;
                }
                if (i < j) {
                    swap(a, i, j, metrics);
                    i++;
                    j--;
                } else break;
            }
            swap(a, lo, j, metrics);

            int leftSize = j - 1 - lo;
            int rightSize = hi - (j + 1);
            if (leftSize < rightSize) {
                quicksort(a, lo, j - 1, metrics);
                quicksort(a, j + 1, hi, metrics);
            } else {
                quicksort(a, j + 1, hi, metrics);
                quicksort(a, lo, j - 1, metrics);
            }
        } finally {
            if (metrics != null) metrics.exit();
        }
    }

    private static void swap(int[] a, int i, int j, Metrics metrics) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        if (metrics != null) metrics.incSwaps();
    }
}
