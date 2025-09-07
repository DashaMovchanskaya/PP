package org.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testSmallX() {
        double result = Arctan.arcTan(8, 0.01);
        assertEquals(Math.atan(0.01), result, 0.0000001);
    }

    @Test
    public void testNegativeX() {
        double result = Arctan.arcTan(10, -0.5);
        assertEquals(Math.atan(-0.5), result, 0.0000001);
    }

    @Test
    public void testZeroX() {
        double result = Arctan.arcTan(5, 0.0);
        assertEquals(0.0, result, 0.0000001);
    }

}
