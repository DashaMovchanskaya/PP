package org.example;

public class SelectionSort implements Sort {
    @Override
    public void sort(int[] a, TriConsumer<int[], Integer, Integer> vis,
                     StopSupplier stop, Runnable delay) {
        int n = a.length;
        for (int i = 0; i < n - 1 && !stop.get(); i++) {
            int minIdx = i;
            for (int j = i + 1; j < n && !stop.get(); j++) {
                if (a[j] < a[minIdx]) minIdx = j;
                vis.accept(a, minIdx, j);
                delay.run();
            }
            int t = a[minIdx]; a[minIdx] = a[i]; a[i] = t;
            vis.accept(a, i, minIdx);
            delay.run();
        }
        vis.accept(a, -1, -1);
    }
}

