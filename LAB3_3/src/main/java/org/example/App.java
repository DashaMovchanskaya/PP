package org.example;

import java.io.*;
import java.util.*;

public class App 
{
    public static Set<Student> union(Set<Student> setA, Set<Student> setB) {
        Set<Student> result = new HashSet<>(setA);
        result.addAll(setB);
        return result;
    }

    public static Set<Student> intersection(Set<Student> setA, Set<Student> setB) {
        Set<Student> result = new HashSet<>(setA);
        result.retainAll(setB);
        return result;
    }

    public static Set<Student> difference(Set<Student> setA, Set<Student> setB) {
        Set<Student> result = new HashSet<>(setA);
        result.removeAll(setB);
        return result;
    }

    public static Set<Student> readFile(String fileName) throws IOException {
        Set<Student> students = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            Student s = Student.fromString(line);
            students.add(s);
        }

        reader.close();
        return students;
    }

    public static void writeFile(String filename, Set<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));

        for (Student s : students) {
            writer.write(s.toString());
            writer.newLine();
        }
        writer.newLine();
        writer.close();
    }


    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя первого файла: ");
        String firstFile = scanner.nextLine().trim();
        System.out.print("Имя второго файла: ");
        String secondFile = scanner.nextLine().trim();
        System.out.print("Имя выходного файла: ");
        String fileOut = scanner.nextLine().trim();

        Set<Student> firstList = readFile(firstFile);
        Set<Student> secondList = readFile(secondFile);

        while (true) {
            System.out.println("\n=== МЕНЮ ОПЕРАЦИЙ ===");
            System.out.println("1 — Объединение (A ∪ B)");
            System.out.println("2 — Пересечение (A ∩ B)");
            System.out.println("3 — Разность (A - B)");
            System.out.println("4 — Выход");
            System.out.print("Выберите операцию: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    Set<Student> result = union(firstList, secondList);
                    writeFile(fileOut, result);
                }
                case "2" -> {
                    Set<Student> result = intersection(firstList, secondList);
                    writeFile(fileOut, result);
                }
                case "3" -> {
                    Set<Student> result = difference(firstList, secondList);
                    writeFile(fileOut, result);
                }
                case "4" -> {
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуй снова.");
            }
        }
    }
}
