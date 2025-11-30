package org.example;
import java.util.function.Consumer;

public interface Sort {
    void sort(int[] a,
              TriConsumer<int[], Integer, Integer> visualize,
              StopSupplier shouldStop,
              Runnable delay);

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    @FunctionalInterface
    interface StopSupplier {
        boolean get();
    }
}
