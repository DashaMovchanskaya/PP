package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFormatorTest {

    @Test
    void testFormatTextSimple() {
        TextFormator tf = new TextFormator(13, 2);
        List<String> result = tf.formatText(List.of("hello world"));
        assertEquals(1, result.size());
        assertEquals("  hello world", result.get(0));
    }

    @Test
    void testFormatTextMultipleLines() {
        TextFormator tf = new TextFormator(13, 2);
        List<String> result = tf.formatText(List.of("hello world test"));
        assertEquals(2, result.size());
        assertEquals("  hello world", result.get(0));
        assertEquals("test", result.get(1));
    }

    @Test
    void testJustifySingleWord() {
        TextFormator tf = new TextFormator(10, 2);
        List<String> result = tf.formatText(List.of("hello"));
        assertEquals(1, result.size());
        assertEquals("  hello", result.get(0));
    }

    @Test
    void testJustifyWhenLineExceedsWidth() {
        TextFormator tf = new TextFormator(16, 2);
        List<String> result = tf.formatText(List.of("hello world"));
        assertEquals(1, result.size());
        assertEquals("  hello world", result.get(0));
    }

    @Test
    void testEmptyInput() {
        TextFormator tf = new TextFormator(10, 2);
        List<String> result = tf.formatText(List.of(""));
        assertEquals(1, result.size());
    }

    @Test
    void testMultipleSpacesBetweenWords() {
        TextFormator tf = new TextFormator(13, 2);
        List<String> result = tf.formatText(List.of("hello    world test"));
        assertEquals(2, result.size());
        assertEquals("  hello world", result.get(0));
        assertEquals("test", result.get(1));
    }

    @Test
    void testLastLineNotJustified() {
        TextFormator tf = new TextFormator(17, 2);
        List<String> result = tf.formatText(List.of("hello world test"));
        assertEquals(2, result.size());
        assertEquals("  hello     world", result.get(0));
        assertEquals("test", result.get(1));
    }

    @Test
    void testSingleLongWord() {
        TextFormator tf = new TextFormator(5, 2);
        List<String> result = tf.formatText(List.of("helloworld"));
        assertEquals(1, result.size());
        assertEquals("  helloworld", result.get(0));
    }

    @Test
    void testMultipleShortWords() {
        TextFormator tf = new TextFormator(15, 2);
        List<String> result = tf.formatText(List.of("a b c d e f g h i j k l m n o p"));
        assertEquals(3, result.size());
        assertEquals(15, result.get(0).length());
    }

    @Test
    void testTwoParagraphs() {
        TextFormator tf = new TextFormator(20, 4);
        List<String> result = tf.formatText(List.of(
                "hello world this is a test",
                "",
                "second paragraph here"
        ));

        assertTrue(result.contains(""));
        assertTrue(result.get(0).startsWith("    "));
        int sepIndex = result.indexOf("");
        assertTrue(result.get(sepIndex + 1).startsWith("    "));
    }

    @Test
    void testIndentRespectedInWidth() {
        TextFormator tf = new TextFormator(15, 3);
        List<String> result = tf.formatText(List.of("one two three four five"));

        assertTrue(result.get(0).startsWith("   "));
        assertTrue(result.get(0).length() <= 15);
    }

    @Test
    void testEmptyListInput() {
        TextFormator tf = new TextFormator(10, 2);
        List<String> result = tf.formatText(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlySpacesInput() {
        TextFormator tf = new TextFormator(10, 2);
        List<String> result = tf.formatText(List.of("     "));

        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    @Test
    void testVeryLongWord() {
        TextFormator tf = new TextFormator(10, 2);
        List<String> result = tf.formatText(List.of("supercalifragilisticexpialidocious"));

        assertEquals("  supercalifragilisticexpialidocious", result.get(0));
    }

    @Test
    void testLastLineNotJustifiedMultipleWords() {
        TextFormator tf = new TextFormator(20, 2);
        List<String> result = tf.formatText(List.of("alpha beta gamma delta"));

        assertTrue(result.get(result.size()-1).trim().equals("delta"));
    }

}

