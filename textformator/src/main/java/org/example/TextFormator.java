package org.example;

import java.util.ArrayList;
import java.util.List;

public class TextFormator {
    private final int width;

    public TextFormator(int width) {
        this.width = width;
    }

    public List<String> formatText(List<String> lines) {
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (String line : lines) {
            String[] words = line.trim().split("\\s+");
            for (String word : words) {
                if (buffer.length() + word.length() + (buffer.length() > 0 ? 1 : 0) > width) {
                    String currentLine = buffer.toString().trim();
                    if (!currentLine.isEmpty()) {
                        if (currentLine.contains(" ")) {
                            result.add(justify(currentLine));
                        } else {
                            result.add(currentLine);
                        }
                    }
                    buffer.setLength(0);
                }
                if (buffer.length() > 0) {
                    buffer.append(" ");
                }
                buffer.append(word);
            }
        }

        if (buffer.length() > 0) {
            result.add(buffer.toString().trim());
        }

        return result;
    }

    private String justify(String line) {
        if (line.length() >= width) return line;

        String[] words = line.split(" ");
        if (words.length == 1) {
            return line;
        }

        int totalSpaces = width - line.replace(" ", "").length();
        int gaps = words.length - 1;
        int spacePerGap = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;

        StringBuilder justified = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            justified.append(words[i]);
            if (i < gaps) {
                int spacesToAdd = spacePerGap + (i < extraSpaces ? 1 : 0);
                justified.append(" ".repeat(spacesToAdd));
            }
        }
        return justified.toString();
    }
}

