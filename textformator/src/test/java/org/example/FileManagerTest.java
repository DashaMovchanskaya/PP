package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    @Test
    void testReadFromFile() throws IOException {
        File tempFile = File.createTempFile("testInput", ".txt");
        Files.write(tempFile.toPath(), List.of("строка1", "строка2"));

        FileManager fm = new FileManager();
        List<String> lines = fm.readFromFile(tempFile.getAbsolutePath());

        assertEquals(2, lines.size());
        assertEquals("строка1", lines.get(0));
        assertEquals("строка2", lines.get(1));
    }

    @Test
    void testWriteLines() throws IOException {
        File tempFile = File.createTempFile("testOutput", ".txt");
        FileManager fm = new FileManager();

        fm.writeLines(List.of("один", "два"), tempFile.getAbsolutePath());

        List<String> lines = Files.readAllLines(tempFile.toPath());
        assertEquals(2, lines.size());
        assertEquals("один", lines.get(0));
        assertEquals("два", lines.get(1));
    }

    @Test
    void testReadFileNotFound() {
        FileManager fm = new FileManager();
        assertThrows(RuntimeException.class,
                () -> fm.readFromFile("несуществующий.txt"));
    }
}
