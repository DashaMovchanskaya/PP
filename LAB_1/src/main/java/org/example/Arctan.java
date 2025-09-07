package org.example;

public class Arctan {

    public static double arcTan(int k, double x) {

        double epsilon = Math.pow(10, -k);

        double arctg = x;
        double summand = x;
        int degree = 1;

        while (Math.abs(summand) >= epsilon) {
            summand *= -1 * x * x * degree;
            degree += 2;
            summand /= degree;
            arctg += summand;
        }

        return arctg;
    }
}
