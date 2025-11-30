package org.example;

public class InsertionSort implements Sort{

    @Override
    public void sort(int[] a, TriConsumer<int[], Integer, Integer> vis,
                     StopSupplier stop, Runnable delay) {
        for (int i = 1; i < a.length && !stop.get(); i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key && !stop.get()) {
                a[j + 1] = a[j];
                j--;
                vis.accept(a, j, i);
                delay.run();
            }
            a[j + 1] = key;
            vis.accept(a, j + 1, i);
            delay.run();
        }
        vis.accept(a, -1, -1);
    }
}
