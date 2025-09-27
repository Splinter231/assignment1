package org.example.closest;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double findClosest(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        return closestRecursive(sortedByX);
    }

    private static double closestRecursive(Point[] points) {
        int n = points.length;
        if (n <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    min = Math.min(min, dist(points[i], points[j]));
                }
            }
            return min;
        }

        int mid = n / 2;
        Point midPoint = points[mid];

        double dLeft = closestRecursive(Arrays.copyOfRange(points, 0, mid));
        double dRight = closestRecursive(Arrays.copyOfRange(points, mid, n));
        double d = Math.min(dLeft, dRight);


        Point[] strip = Arrays.stream(points)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        double minStrip = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                minStrip = Math.min(minStrip, dist(strip[i], strip[j]));
            }
        }

        return Math.min(d, minStrip);
    }
}
