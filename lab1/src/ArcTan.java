//Movchanskaya Daria, 6 group 

import java.util.Scanner;

public class ArcTan {

    public static double arcTan(int k, double x){

        double epsilon = Math.pow(10, -k);

        double arctg = x;
        double summand = x;
        int degree = 1;

        while(Math.abs(summand) >= epsilon){
            summand *= -1 * x * x * degree;
            degree += 2;
            summand /= degree;
            arctg += summand;
        }

       return arctg;
    }
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите натуральное число k: ");
            int k;
            if(scanner.hasNextInt()){
                k = scanner.nextInt();
                if(k <= 0){
                    throw new IllegalArgumentException("k должно быть натуральным числом.");
                }
            }
            else{
                throw new IllegalArgumentException("k должно быть натуральным числом.");
            }

            System.out.print("Введите число x из интервала (-1; 1): ");
            double x = scanner.nextDouble();
            if(x <= -1 || x >= 1){
                throw new IllegalArgumentException("x должно принадлежать интервалу (-1; 1).");
            }

            System.out.printf("Результат по формуле Тейлора: arctan(%.3f) = %.3f%n", x,  arcTan(k, x));
            System.out.printf("Рзультат с помощью функции: arctan(%.3f) = %.3f%n", x, Math.atan(x));
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}
