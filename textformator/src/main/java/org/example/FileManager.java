package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public List<String> readFromFile(String inputFileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public void writeLines(List<String> lines, String outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
