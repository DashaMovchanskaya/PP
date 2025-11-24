package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class App extends JPanel implements ActionListener, KeyListener {
    private static final int CELL_SIZE = 20;
    private static final int COLS = 30;
    private static final int ROWS = 30;
    private static final int WIDTH = COLS * CELL_SIZE;
    private static final int HEIGHT = ROWS * CELL_SIZE;

    private static final int TICK_MS = 120;

    private Deque<Point> snake = new ArrayDeque<>();
    private Direction dir = Direction.RIGHT;
    private Point food;
    private boolean gameOver = false;
    private final Timer timer = new Timer(TICK_MS, this);
    private final Random rnd = new Random();

    private enum Direction {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
        final int dx, dy;
        Direction(int dx, int dy) { this.dx = dx; this.dy = dy; }
    }

    public App() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        resetGame();
        timer.start();
    }

    private void resetGame() {
        snake.clear();

        snake.add(new Point(COLS/2 - 1, ROWS/2 - 1));
        snake.add(new Point(COLS/2, ROWS/ 2));
        snake.add(new Point(COLS/2 + 1, ROWS/ 2));

        dir = Direction.RIGHT;
        gameOver = false;
        placeFood();
    }

    private void placeFood() {
        while (true) {
            int x = rnd.nextInt(COLS);
            int y = rnd.nextInt(ROWS);
            Point p = new Point(x, y);
            if (!snake.contains(p)) {
                food = p;
                break;
            }
        }
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(40, 40, 40));
        for (int i = 0; i <= COLS; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, HEIGHT);
        }
        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(0, i * CELL_SIZE, WIDTH, i * CELL_SIZE);
        }

        if (food != null) {
            g.setColor(Color.RED);
            fillCell(g, food.x, food.y);
        }

        int i = 0;
        for (Point p : snake) {
            g.setColor(i == snake.size() - 1 ? Color.GREEN : new Color(0, 160, 0));
            fillCell(g, p.x, p.y);
            i++;
        }

        if (gameOver) {
            drawCenteredText(g, "Game Over — пробел для рестарта", Color.WHITE);
        }
    }

    private void fillCell(Graphics g, int cx, int cy) {
        g.fillRect(cx * CELL_SIZE + 1, cy * CELL_SIZE + 1, CELL_SIZE - 2, CELL_SIZE - 2);
    }

    private void drawCenteredText(Graphics g, String text, Color color) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 18f));
        FontMetrics fm = g.getFontMetrics();
        int x = (WIDTH - fm.stringWidth(text)) / 2;
        int y = (HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            repaint();
            return;
        }

        Point head = snake.peekLast();
        int nx = head.x + dir.dx;
        int ny = head.y + dir.dy;
        Point next = new Point(nx, ny);

        if (nx < 0 || nx >= COLS || ny < 0 || ny >= ROWS) {
            gameOver = true;
            repaint();
            return;
        }

        if (snake.contains(next)) {
            gameOver = true;
            repaint();
            return;
        }

        snake.addLast(next);

        if (food != null && next.equals(food)) {
            placeFood();
        } else {
            snake.removeFirst();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                if (dir != Direction.DOWN) dir = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if (dir != Direction.UP) dir = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                if (dir != Direction.RIGHT) dir = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if (dir != Direction.LEFT) dir = Direction.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                if (gameOver) resetGame();
                break;
            default:
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Snake");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.add(new App());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

