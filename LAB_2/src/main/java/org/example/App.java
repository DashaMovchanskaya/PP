package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 *  Найти максимальное из чисел, встречающихся в заданной матрице более одного раза.
 *  Размерность матрицы ввести с клавиатуры. Элементы матрицы задать случайным образом.
 */
public class App {
    public static void validateInput(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Размерность должна быть натуральной.");
        }
    }

    public static int searchMaxNotUnique(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int maxNumber = -1;

        int[] array = new int [rows * columns];
        int size = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[size++] = matrix[i][j];
            }
        }

        Arrays.sort(array);

        for (int i = size - 1; i > 0; i--) {
            if (array[i] == array[i - 1]){
                maxNumber = array[i];
                break;
            }
        }

        return maxNumber;
    }

    public static void main( String[] args ) {
        try {
            Scanner scanner = new Scanner(System.in);

            int rows, columns;
            System.out.println("Введите размеры матрицы.");
            System.out.print("Введите количество строк матрицы: ");
            rows = scanner.nextInt();
            System.out.print("Введите количество столбцов матрицы: ");
            columns = scanner.nextInt();

            validateInput(rows, columns);

            int[][] matrix = new int[rows][columns];
            Random random = new Random();
            System.out.println("Матрица:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = random.nextInt(100);
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print("\n");
            }

            int maxNumber = searchMaxNotUnique(matrix);
            if(maxNumber != -1){
                System.out.println("Максимальное из чисел, встречающихся в заданной матрице более одного раза: " + maxNumber);
            }
            else{
                System.out.println("Все элементы матрицы уникальны!");
            }

        }
        catch(Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

}
