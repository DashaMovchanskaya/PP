package org.example;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayPanelTest {

    @Test
    void testSetArray() {
        ArrayPanel panel = new ArrayPanel();
        int[] testArray = {10, 20, 30};

        panel.setArray(testArray);

        assertArrayEquals(testArray, panel.getArray());
        assertNotSame(testArray, panel.getArray());
    }

    @Test
    void testUpdateWithHighlight() {
        ArrayPanel panel = new ArrayPanel();
        int[] initial = {1, 2, 3};
        int[] updated = {3, 2, 1};

        panel.setArray(initial);
        panel.update(updated, 0, 2);

        assertArrayEquals(updated, panel.getArray());
    }

    @Test
    void testPaintComponent() {
        ArrayPanel panel = new ArrayPanel();
        panel.setArray(new int[]{100, 200, 300});

        Graphics mockGraphics = new MockGraphics();

        assertDoesNotThrow(() -> panel.paintComponent(mockGraphics));
    }

    @Test
    void testEmptyArrayPainting() {
        ArrayPanel panel = new ArrayPanel();
        panel.setArray(new int[0]);

        Graphics mockGraphics = new MockGraphics();
        assertDoesNotThrow(() -> panel.paintComponent(mockGraphics));
    }

    @Test
    void testNullArrayPainting() {
        ArrayPanel panel = new ArrayPanel();

        Graphics mockGraphics = new MockGraphics();
        assertDoesNotThrow(() -> panel.paintComponent(mockGraphics));
    }

    private static class MockGraphics extends Graphics {
        @Override
        public Graphics create() { return this; }
        @Override
        public void translate(int x, int y) {}
        @Override
        public Color getColor() { return Color.BLACK; }
        @Override
        public void setColor(Color c) {}
        @Override
        public void setPaintMode() {}
        @Override
        public void setXORMode(Color c1) {}
        @Override
        public Font getFont() { return new Font("Arial", Font.PLAIN, 12); }
        @Override
        public void setFont(Font font) {}
        @Override
        public FontMetrics getFontMetrics(Font f) { return null; }
        @Override
        public Rectangle getClipBounds() { return new Rectangle(0, 0, 100, 100); }
        @Override
        public void clipRect(int x, int y, int width, int height) {}
        @Override
        public void setClip(int x, int y, int width, int height) {}
        @Override
        public Shape getClip() { return new Rectangle(0, 0, 100, 100); }
        @Override
        public void setClip(Shape clip) {}
        @Override
        public void copyArea(int x, int y, int width, int height, int dx, int dy) {}
        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {}
        @Override
        public void fillRect(int x, int y, int width, int height) {}
        @Override
        public void clearRect(int x, int y, int width, int height) {}
        @Override
        public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}
        @Override
        public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}
        @Override
        public void drawOval(int x, int y, int width, int height) {}
        @Override
        public void fillOval(int x, int y, int width, int height) {}
        @Override
        public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}
        @Override
        public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}
        @Override
        public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {}
        @Override
        public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {}
        @Override
        public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {}
        @Override
        public void drawString(String str, int x, int y) {}
        @Override
        public void drawString(AttributedCharacterIterator iterator, int x, int y) {}
        @Override
        public boolean drawImage(Image img, int x, int y, ImageObserver observer) { return false; }
        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) { return false; }
        @Override
        public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) { return false; }
        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) { return false; }
        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) { return false; }
        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) { return false; }
        @Override
        public void dispose() {}
    }
}