package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static List<String> input() throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            String[] words = line.split("[\\s,.?!:;\"'—\\-…]+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    list.add(word);
                }
            }
        }

        return list;
    }

    public static List<String> sort(Map<String, Integer> dictionary) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dictionary.entrySet());

        entries.sort((a, b) -> {
            int compare = b.getValue().compareTo(a.getValue());
            if (compare == 0) {
                return a.getKey().compareTo(b.getKey()); // Сортируем по ключу, если значения равны
            }
            return compare;
        });

        List<String> sorted = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sorted.add(entry.getKey());
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return sorted;
    }

    public static List<String> countOccurrence(List<String> wordList, List<String> textList) {
        Map<String, Integer> dictionary = new HashMap<>();
        for (String word : wordList) {
            String lowerWord = word.toLowerCase();
            int count = 0;
            for (String textWord : textList) {
                if (lowerWord.equals(textWord.toLowerCase())) {
                    count++;
                }
            }
            dictionary.put(lowerWord, count);
        }

        return sort(dictionary);
    }

    public static void main( String[] args ) throws IOException {
        System.out.println("Введите текст (завершите ввод пустой строкой):");
        List<String> textWordList = input();

        System.out.println("Введите список слов (завершите ввод пустой строкой):");
        List<String> wordList = input();

        System.out.println("\nРезультат:");

        if (textWordList.isEmpty()) {
            System.out.println("В тексте нет слов!");
        }

        if (wordList.isEmpty()) {
            System.out.println("В списке нет слов!");
            return;
        }

        List<String> result = countOccurrence(wordList, textWordList);

        for(String word : result) {
            System.out.print(word + " ");
        }

    }
}
