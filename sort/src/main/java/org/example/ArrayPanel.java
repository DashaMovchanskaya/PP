package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ArrayPanel extends JPanel {
    private int[] arr = new int[0];
    private int hiA = -1, hiB = -1;

    public void setArray(int[] a) {
        this.arr = Arrays.copyOf(a, a.length);
        hiA = hiB = -1;
        repaint();
    }

    public int[] getArray() { return arr; }

    public void update(int[] a, int i, int j) {
        this.arr = Arrays.copyOf(a, a.length);
        this.hiA = i;
        this.hiB = j;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (arr == null || arr.length == 0) return;

        int w = getWidth();
        int h = getHeight();
        int barW = Math.max(1, w / arr.length);
        int max = Arrays.stream(arr).max().orElse(1);

        for (int i = 0; i < arr.length; i++) {
            int barH = (int) ((arr[i] * 1.0 / max) * (h - 20));
            int x = i * barW;
            int y = h - barH;

            if (i == hiA || i == hiB) g.setColor(Color.GREEN);
            else g.setColor(Color.MAGENTA);

            g.fillRect(x, y, barW - 1, barH);
        }
    }
}
