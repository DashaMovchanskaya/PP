package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void multiplySimplePolynomials() {
        List<Integer> a = Arrays.asList(1, 2);
        List<Integer> b = Arrays.asList(3, 4);

        List<Integer> result = App.multiplyPolynomials(a, b);
        List<Integer> expected = Arrays.asList(3, 10, 8);

        assertEquals(expected, result);
    }

    @Test
    public void multiplyWithZeroPolynomial() {
        List<Integer> a = Arrays.asList(0, 0, 0);
        List<Integer> b = Arrays.asList(1, 2, 3);

        List<Integer> result = App.multiplyPolynomials(a, b);
        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0);

        assertEquals(expected, result);
    }

    @Test
    public void testMultiplySingleTermPolynomials() {
        List<Integer> a = Arrays.asList(0, 0, 5);
        List<Integer> b = Arrays.asList(0, 3);

        List<Integer> result = App.multiplyPolynomials(a, b);
        List<Integer> expected = Arrays.asList(0, 0, 0, 15);

        assertEquals(expected, result);
    }

    @Test
    public void multiplyEmptyPolynomials() {
        List<Integer> a = Arrays.asList();
        List<Integer> b = Arrays.asList();

        List<Integer> result = App.multiplyPolynomials(a, b);
        List<Integer> expected = Arrays.asList();

        assertEquals(expected, result);
    }

    @Test
    public void testMultiplyConstantPolynomials() {
        List<Integer> a = Arrays.asList(2);
        List<Integer> b = Arrays.asList(3);

        List<Integer> result = App.multiplyPolynomials(a, b);
        List<Integer> expected = Arrays.asList(6);

        assertEquals(expected, result);
    }

}
