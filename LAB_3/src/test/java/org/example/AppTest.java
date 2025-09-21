package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void normalLine()
    {
        String input = "hello world";
        String expected = "world hello";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void singleWord()
    {
        String input = "hello";
        String expected = "hello";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void lineWithCommasAndDots()
    {
        String input = "one, two. three";
        String expected = "three two one";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void twoCommas()
    {
        String input = "one,,two. three";
        String expected = "three two one";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void twoSpaces()
    {
        String input = "one  two. three";
        String expected = "three two one";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void emptyLine()
    {
        String input = "";
        String expected = "";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void onlyDelimitersLine()
    {
        String input = ",.,.,..,.,,  ,.,.";
        String expected = "";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void russianLine()
    {
        String input = "привет, мир";
        String expected = "мир привет";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void trailingDelimiters() {
        String input = "start middle end.";
        String expected = "end middle start";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void leadingSpaces() {
        String input = "   hello world";
        String expected = "world hello";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void tabsBetweenWords() {
        String input = "one\ttwo\tthree";
        String expected = "three two one";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

    @Test
    public void mixedCaseWords() {
        String input = "hello WORLD";
        String expected = "WORLD hello";
        assertEquals(expected, App.reverseWordsInLine(input));
    }

}
