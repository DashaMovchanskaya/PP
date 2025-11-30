package org.example;

public class BubbleSort implements Sort {
    @Override
    public void sort(int[] a, TriConsumer<int[], Integer, Integer> vis,
                     StopSupplier stop, Runnable delay) {
        int n = a.length;
        for (int i = 0; i < n - 1 && !stop.get(); i++) {
            for (int j = 0; j < n - i - 1 && !stop.get(); j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                }
                vis.accept(a, j, j + 1);
                delay.run();
            }
        }
        vis.accept(a, -1, -1);
    }
}
