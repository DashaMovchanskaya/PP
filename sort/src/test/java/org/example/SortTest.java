package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void testBubbleSort() {
        testSortAlgorithm(new BubbleSort());
    }

    @Test
    void testInsertionSort() {
        testSortAlgorithm(new InsertionSort());
    }

    @Test
    void testSelectionSort() {
        testSortAlgorithm(new SelectionSort());
    }

    @Test
    void testMergeSort() {
        testSortAlgorithm(new MergeSort());
    }

    private void testSortAlgorithm(Sort sorter) {
        int[] arr = {5, 2, 8, 1, 9, 3};
        int[] expected = {1, 2, 3, 5, 8, 9};

        AtomicInteger callCount = new AtomicInteger();

        sorter.sort(arr,
                (array, i, j) -> callCount.incrementAndGet(),
                () -> false,
                () -> {}
        );

        assertArrayEquals(expected, arr);
        assertTrue(callCount.get() > 0, "Visualization should be called");
    }

    @Test
    @Timeout(5)
    void testSortWithStopFlag() {
        BubbleSort sorter = new BubbleSort();
        int[] arr = {5, 2, 8, 1, 9, 3};
        AtomicBoolean stopped = new AtomicBoolean(false);

        sorter.sort(arr,
                (array, i, j) -> {},
                () -> {
                    stopped.set(true);
                    return true;
                },
                () -> {}
        );

        assertTrue(stopped.get(), "Sorting should respect stop flag");
    }

    @Test
    void testEmptyArray() {
        testWithArray(new int[0], new BubbleSort());
    }

    @Test
    void testSingleElement() {
        testWithArray(new int[]{42}, new BubbleSort());
    }

    @Test
    void testAlreadySorted() {
        testWithArray(new int[]{1, 2, 3, 4, 5}, new BubbleSort());
    }

    @Test
    void testReverseSorted() {
        testWithArray(new int[]{5, 4, 3, 2, 1}, new BubbleSort());
    }

    private void testWithArray(int[] arr, Sort sorter) {
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        sorter.sort(arr,
                (array, i, j) -> {},
                () -> false,
                () -> {}
        );

        assertArrayEquals(expected, arr);
    }
}