package org.example;

public class MergeSort implements Sort {
    @Override
    public void sort(int[] a, TriConsumer<int[], Integer, Integer> vis,
                     StopSupplier stop, Runnable delay) {
        mergeSort(a, 0, a.length - 1, vis, stop, delay);
        vis.accept(a, -1, -1);
    }

    private void mergeSort(int[] a, int l, int r,
                           TriConsumer<int[], Integer, Integer> vis,
                           StopSupplier stop, Runnable delay) {
        if (stop.get()) return;
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m, vis, stop, delay);
        mergeSort(a, m + 1, r, vis, stop, delay);
        merge(a, l, m, r, vis, stop, delay);
    }

    private void merge(int[] a, int l, int m, int r,
                       TriConsumer<int[], Integer, Integer> vis,
                       StopSupplier stop, Runnable delay) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = new int[n1], R = new int[n2];
        System.arraycopy(a, l, L, 0, n1);
        System.arraycopy(a, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2 && !stop.get()) {
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
            vis.accept(a, k - 1, l);
            delay.run();
        }
        while (i < n1 && !stop.get()) {
            a[k++] = L[i++];
            vis.accept(a, k - 1, l);
            delay.run();
        }
        while (j < n2 && !stop.get()) {
            a[k++] = R[j++];
            vis.accept(a, k - 1, l);
            delay.run();
        }
    }
}

