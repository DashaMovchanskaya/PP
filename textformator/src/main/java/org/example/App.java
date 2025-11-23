package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ширину строки: ");
        int width = Integer.parseInt(sc.nextLine());

        FileManager manager = new FileManager();
        TextFormator formatter = new TextFormator(width);
        System.out.print("Введите название входного файла: ");
        String inFile = sc.nextLine();
        System.out.print("Введите название выходного файла: ");
        String outFile = sc.nextLine();

        try {
            List<String> lines = manager.readFromFile(inFile);
            List<String> formatted = formatter.formatText(lines);
            manager.writeLines(formatted, outFile);
            System.out.println("Текст успешно выровнен и записан в output.txt");
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлами: " + e.getMessage());
        }
    }
}
