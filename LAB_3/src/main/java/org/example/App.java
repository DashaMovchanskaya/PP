package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Разработать консольное приложение на Java.
 * Текст – это множество строк, введенных с клавиатуры. Разделители: пробел, запятая, точка.
 * Признак конца ввода текста – пустая строка.
 * Варианты:
 * В каждой строке текста переставить слова в обратном порядке.
 *
 */

public class App 
{
    public static String reverseWordsInLine(String line) {
        String[] words = line.split("[\\s,.]+");
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordList.add(word);
            }
        }
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main( String[] args ) throws IOException {
        System.out.println("Введите текст:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lineList = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            lineList.add(line);
        }

        System.out.println("\nРезультат:");

        for (String line : lineList) {
            if (!line.isEmpty()) {
                String newLine = reverseWordsInLine(line);
                System.out.println(newLine);
            }
        }

    }

}
