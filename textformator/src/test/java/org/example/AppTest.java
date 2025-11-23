package org.example;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testFullPipeline() throws Exception {
        File input = File.createTempFile("input", ".txt");
        File output = File.createTempFile("output", ".txt");
        Files.write(input.toPath(), List.of("Java is cool", "JUnit are the best test tool"));

        FileManager fm = new FileManager();
        TextFormator tf = new TextFormator(12);

        List<String> lines = fm.readFromFile(input.getAbsolutePath());
        List<String> formatted = tf.formatText(lines);
        fm.writeLines(formatted, output.getAbsolutePath());

        List<String> result = Files.readAllLines(output.toPath());
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).length() <= 12);
    }
}
