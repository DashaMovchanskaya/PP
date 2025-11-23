package org.example;

import java.util.ArrayList;
import java.util.List;

public class TextFormator {
    private final int width;
    private final int indent;

    public TextFormator(int width, int indent) {
        if (width <= 0) throw new IllegalArgumentException("Ширина должна быть > 0");
        if (indent < 0) throw new IllegalArgumentException("Отступ не может быть отрицательным");
        if (indent >= width) throw new IllegalArgumentException("Отступ должен быть меньше ширины строки");
        this.width = width;
        this.indent = indent;
    }

    public List<String> formatText(List<String> lines) {
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        boolean firstLine = true;

        for (String line : lines) {
            int currentWidth = firstLine ? width - indent : width;
            if (line.trim().isEmpty()) {
                if (buffer.length() > 0) {
                    result.add(applyIndent(buffer.toString().trim(), firstLine));
                    buffer.setLength(0);
                }
                result.add("");
                firstLine = true;
                continue;
            }
            String[] words = line.trim().split("\\s+");
            for (String word : words) {
                if (buffer.length() + word.length() + (buffer.length() > 0 ? 1 : 0) > currentWidth ) {
                    String currentLine = buffer.toString().trim();
                    if (!currentLine.isEmpty()) {
                        if (currentLine.contains(" ")) {
                            result.add(justify(currentLine, firstLine, currentWidth));
                        } else {
                            result.add(applyIndent(currentLine, firstLine));
                        }
                        firstLine = false;
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
            result.add(applyIndent(buffer.toString().trim(), firstLine));
        }

        return result;
    }

    private String justify(String line, boolean firstLine, int currentWidth) {
        if (line.length() >= currentWidth) return applyIndent(line, firstLine);

        String[] words = line.split(" ");
        if (words.length == 1) {
            return applyIndent(line, firstLine);
        }

        int totalSpaces = currentWidth - line.replace(" ", "").length();
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

        return applyIndent(justified.toString(), firstLine);
    }


    private String applyIndent(String line, boolean firstLine) {
        if (firstLine) {
            return " ".repeat(indent) + line;
        }
        return line;
    }

}

