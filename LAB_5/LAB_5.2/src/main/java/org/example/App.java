package org.example;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static List<Integer> multiplyPolynomials(List<Integer> firstPolynomial, List<Integer> secondPolynomial) {
        if (firstPolynomial.isEmpty() || secondPolynomial.isEmpty()) {
            return new ArrayList<>();
        }
        int resultSize = firstPolynomial.size() + secondPolynomial.size() - 1;
        List<Integer> result = new ArrayList<> (resultSize);

        for (int i = 0; i < resultSize; i++) {
            result.add(0);
        }

        for (int i = 0; i < firstPolynomial.size(); i++) {
            for (int j = 0; j < secondPolynomial.size(); j++) {
                int index = i + j;
                result.set(index, result.get(index) + firstPolynomial.get(i) * secondPolynomial.get(j));
            }
        }

        return result;
    }

    public static List<Integer> input(String polynomialName) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> polynomial = new ArrayList<>();
        System.out.println("Введите степень " + polynomialName + " многочлена:");
        int size;

        while(true){
            if(scanner.hasNextInt()) {
                size = scanner.nextInt() + 1;
                if(size > 0) {
                    break;
                }
                else {
                    System.out.println("Ошибка! Степень должна быть неотрицательной. Попробуйте снова: ");
                }
            }
            else {
                System.out.println("Ошибка! Степень должна быть целой. Попробуйте снова: ");
                scanner.next();
            }
        }

        System.out.println("Введите коэффициенты " + polynomialName + " многочлена (их на 1 больше, чем степень):");
        for (int i = 0; i < size; i++) {
            System.out.print("Введите коэффициент при x^" + i + ": ");
            polynomial.add(scanner.nextInt());
        }

        return polynomial;
    }

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstPolynomial = input("первого");
        List<Integer> secondPolynomial = input("второго");

        List<Integer> result = multiplyPolynomials(firstPolynomial, secondPolynomial);

        System.out.println("Результат умножения двух многочленов:");
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.print(result.get(i) + "x^" + i + " + ");
        }

        if(result.size() >= 1) {
            System.out.print(result.get(result.size() - 1) + "x^" + (result.size() - 1));
        }
    }
}
