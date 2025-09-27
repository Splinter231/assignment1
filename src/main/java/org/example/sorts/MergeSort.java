package org.example.sorts;

import org.example.metrics.Metrics;
import java.util.Objects;

public final class MergeSort {
    private static final int INSERTION_SORT_CUTOFF = 16;

    private MergeSort() {}

    public static void sort(int[] a, Metrics metrics) {
        Objects.requireNonNull(a);
        if (a.length <= 1) return;

        int[] aux = new int[a.length];
        if (metrics != null) metrics.addAllocations(1);
        mergeSortRec(a, aux, 0, a.length, metrics);
    }

    private static void mergeSortRec(int[] a, int[] aux, int lo, int hi, Metrics metrics) {
        if (metrics != null) metrics.enter();
        try {
            int len = hi - lo;
            if (len <= INSERTION_SORT_CUTOFF) {
                insertionSort(a, lo, hi, metrics);
                return;
            }
            int mid = (lo + hi) >>> 1;
            mergeSortRec(a, aux, lo, mid, metrics);
            mergeSortRec(a, aux, mid, hi, metrics);

            if (metrics != null) metrics.incComparisons();
            if (a[mid - 1] <= a[mid]) {
                return;
            }

            int i = lo, j = mid, k = lo;
            while (i < mid && j < hi) {
                if (metrics != null) metrics.incComparisons();
                if (a[i] <= a[j]) {
                    aux[k++] = a[i++];
                } else {
                    aux[k++] = a[j++];
                }
            }
            while (i < mid) {
                aux[k++] = a[i++];
            }
            while (j < hi) {
                aux[k++] = a[j++];
            }
            System.arraycopy(aux, lo, a, lo, len);
        } finally {
            if (metrics != null) metrics.exit();
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i < hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                if (metrics != null) metrics.incComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j]; // shift
                    if (metrics != null) metrics.incSwaps();
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}
