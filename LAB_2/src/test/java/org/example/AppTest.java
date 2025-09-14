package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void maxDuplicateExists() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 2}
        };
        int result = App.searchMaxNotUnique(matrix);
        assertEquals(2, result);
    }

    @Test
    public void noDuplicates() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int result = App.searchMaxNotUnique(matrix);
        assertEquals(-1, result);
    }

    @Test
    public void multipleDuplicates() {
        int[][] matrix = {
                {7, 8, 9},
                {9, 8, 7}
        };
        int result = App.searchMaxNotUnique(matrix);
        assertEquals(9, result); // максимальное из повторяющихся
    }

    @Test
    public void allSameValues() {
        int[][] matrix = {
                {5, 5},
                {5, 5}
        };
        int result = App.searchMaxNotUnique(matrix);
        assertEquals(5, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidInput() {
        App.validateInput(0, 5); // должно выбросить исключение
    }
}