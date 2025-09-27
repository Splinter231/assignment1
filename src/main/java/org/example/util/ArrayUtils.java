package org.example.util;

import org.example.metrics.Metrics;
import java.util.Random;

public final class ArrayUtils {
    private static final Random rnd = new Random();

    private ArrayUtils() {}


    public static void swap(int[] a, int i, int j, Metrics metrics) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        if (metrics != null) metrics.incSwaps();
    }


    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }


    public static int partition(int[] a, int lo, int hi, int pivotIndex, Metrics metrics) {

        swap(a, pivotIndex, hi, metrics);
        int pivot = a[hi];
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (metrics != null) metrics.incComparisons();
            if (a[i] < pivot) {
                swap(a, i, store, metrics);
                store++;
            }
        }
        swap(a, store, hi, metrics);
        return store;
    }


    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }
}
