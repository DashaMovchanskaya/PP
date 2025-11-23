package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFormatorTest {

    @Test
    void testFormatTextSimple() {
        TextFormator tf = new TextFormator(11);
        List<String> result = tf.formatText(List.of("hello world"));
        assertEquals(1, result.size());
        assertEquals("hello world", result.get(0));
    }

    @Test
    void testFormatTextMultipleLines() {
        TextFormator tf = new TextFormator(11);
        List<String> result = tf.formatText(List.of("hello world test"));
        assertEquals(2, result.size());
        assertEquals("hello world", result.get(0));
        assertEquals("test", result.get(1));
    }


    @Test
    void testJustifySingleWord() {
        TextFormator tf = new TextFormator(10);
        List<String> result = tf.formatText(List.of("hello"));
        assertEquals(1, result.size());
        assertEquals("hello", result.get(0));
    }

    @Test
    void testJustifyWhenLineExceedsWidth() {
        TextFormator tf = new TextFormator(14);
        List<String> result = tf.formatText(List.of("hello world"));
        assertEquals(1, result.size());
        assertEquals("hello world", result.get(0));
    }

    @Test
    void testEmptyInput() {
        TextFormator tf = new TextFormator(10);
        List<String> result = tf.formatText(List.of(""));
        assertEquals(0, result.size());
    }

    @Test
    void testLongTextWithExactWidth() {
        TextFormator tf = new TextFormator(11);
        List<String> result = tf.formatText(List.of("hello world"));
        assertEquals(1, result.size());
        assertEquals("hello world", result.get(0));
    }

    @Test
    void testMultipleSpacesBetweenWords() {
        TextFormator tf = new TextFormator(11);
        List<String> result = tf.formatText(List.of("hello    world test"));
        assertEquals(2, result.size());
        assertEquals("hello world", result.get(0));
        assertEquals("test", result.get(1));
    }

    @Test
    void testLastLineNotJustified() {
        TextFormator tf = new TextFormator(15);
        List<String> result = tf.formatText(List.of("hello world test"));
        assertEquals(2, result.size());
        assertEquals("hello     world", result.get(0));
        assertEquals("test", result.get(1));
    }

    @Test
    void testSingleLongWord() {
        TextFormator tf = new TextFormator(5);
        List<String> result = tf.formatText(List.of("helloworld"));
        assertEquals(1, result.size());
        assertEquals("helloworld", result.get(0));
    }

    @Test
    void testMultipleShortWords() {
        TextFormator tf = new TextFormator(15);
        List<String> result = tf.formatText(List.of("a b c d e f g h i j k l m n o p"));
        assertEquals(2, result.size());
        assertEquals(15, result.get(0).length());
    }
}

