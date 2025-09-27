package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void countOccurrence_basicCase()
    {
        List<String> wordList = Arrays.asList("apple", "banana", "orange");
        List<String> textList = Arrays.asList("banana", "apple", "apple", "orange", "apple");

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana", "orange"), result);
    }

    @Test
    public void countOccurrence_caseInsensitive() {
        List<String> wordList = Arrays.asList("Apple", "BANANA");
        List<String> textList = Arrays.asList("apple", "banana", "Banana", "APPLE");

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    public void countOccurrence_wordNotFound() {
        List<String> wordList = Arrays.asList("kiwi", "mango");
        List<String> textList = Arrays.asList("apple", "banana", "orange");

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("kiwi", "mango"), result);
    }

    @Test
    public void countOccurrence_duplicateWordsInWordList() {
        List<String> wordList = Arrays.asList("apple", "apple", "banana");
        List<String> textList = Arrays.asList("apple", "banana", "apple");

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    public void countOccurrence_emptyText() {
        List<String> wordList = Arrays.asList("apple", "banana");
        List<String> textList = Collections.emptyList();

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    public void countOccurrence_emptyWordList() {
        List<String> wordList = Collections.emptyList();
        List<String> textList = Arrays.asList("apple", "banana");

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void sort_correctOrder() {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("apple", 3);
        dictionary.put("banana", 2);
        dictionary.put("orange", 1);

        List<String> sorted = App.sort(dictionary);

        assertEquals(Arrays.asList("apple", "banana", "orange"), sorted);
    }

    @Test
    public void sort_withEqualCounts() {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("apple", 2);
        dictionary.put("banana", 2);
        dictionary.put("orange", 2);

        List<String> sorted = App.sort(dictionary);

        assertTrue(sorted.containsAll(Arrays.asList("apple", "banana", "orange")));
        assertEquals(3, sorted.size());
    }

    @Test
    public void sort_zeroCountsIncluded() {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("apple", 0);
        dictionary.put("banana", 2);

        List<String> sorted = App.sort(dictionary);

        assertEquals(Arrays.asList("banana", "apple"), sorted);
    }

    @Test
    public void sort_emptyDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();

        List<String> sorted = App.sort(dictionary);

        assertTrue(sorted.isEmpty());
    }

    @Test
    public void sort_singleElement() {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("apple", 1);

        List<String> sorted = App.sort(dictionary);

        assertEquals(Arrays.asList("apple"), sorted);
    }

    @Test
    public void sort_stableSort() {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("apple", 2);
        dictionary.put("banana", 2);
        dictionary.put("orange", 1);

        List<String> sorted = App.sort(dictionary);

        assertEquals("apple", sorted.get(0));
        assertTrue(sorted.subList(0, 2).containsAll(Arrays.asList("apple", "banana")));
    }

    @Test
    public void inputParsing_punctuationHandling() {
        String line = "Hello, world! How's it going?";
        String[] words = line.split("[\\s,.?!:;\"'—\\-…]+");

        assertArrayEquals(new String[]{"Hello", "world", "How", "s", "it", "going"}, words);
    }

    @Test
    public void main_full() throws IOException {
        String textInput = "apple banana apple\n\n";
        String wordInput = "banana apple\n\n";

        System.setIn(new ByteArrayInputStream(textInput.getBytes()));
        List<String> textList = App.input();

        System.setIn(new ByteArrayInputStream(wordInput.getBytes()));
        List<String> wordList = App.input();

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    public void main_emptyTextAndWordList() throws IOException {
        String textInput = "\n";
        String wordInput = "\n";

        System.setIn(new ByteArrayInputStream(textInput.getBytes()));
        List<String> textList = App.input();

        System.setIn(new ByteArrayInputStream(wordInput.getBytes()));
        List<String> wordList = App.input();

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    public void main_textWithNoWords() throws IOException {
        String textInput = "This is a test.\n\n";
        String wordInput = "apple orange\n\n";

        System.setIn(new ByteArrayInputStream(textInput.getBytes()));
        List<String> textList = App.input();

        System.setIn(new ByteArrayInputStream(wordInput.getBytes()));
        List<String> wordList = App.input();

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "orange"), result);
    }

    @Test
    public void main_repeatedWords() throws IOException {
        String textInput = "apple apple banana apple\n\n";
        String wordInput = "apple banana\n\n";

        System.setIn(new ByteArrayInputStream(textInput.getBytes()));
        List<String> textList = App.input();

        System.setIn(new ByteArrayInputStream(wordInput.getBytes()));
        List<String> wordList = App.input();

        List<String> result = App.countOccurrence(wordList, textList);

        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    public void main_largeInput() throws IOException {
        StringBuilder textInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            textInput.append("apple banana orange ");
        }
        textInput.append("\n");

        String wordInput = "apple banana grape\n\n";

        System.setIn(new ByteArrayInputStream(textInput.toString().getBytes()));
        List<String> textList = App.input();

        System.setIn(new ByteArrayInputStream(wordInput.getBytes()));
        List<String> wordList = App.input();

        List<String> result = App.countOccurrence(wordList, textList);
        assertEquals(Arrays.asList("apple", "banana", "grape"), result);
    }
}
