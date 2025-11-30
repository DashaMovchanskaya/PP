package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class App extends JFrame{
    private final ArrayPanel arrayPanel = new ArrayPanel();
    private final JComboBox<String> algoBox = new JComboBox<>(new String[]{"Bubble", "Insertion", "Selection", "Merge"});
    private final JSlider speedSlider = new JSlider(0, 100, 50);
    private final JButton genBtn = new JButton("Generate");
    private final JButton sortBtn = new JButton("Sort");
    private final JButton stopBtn = new JButton("Stop");

    private Thread worker;
    private volatile boolean stopFlag = false;

    public App() {
        super("Sort Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        JPanel controls = new JPanel();
        controls.add(new JLabel("Algorithm:"));
        controls.add(algoBox);
        controls.add(new JLabel("Speed:"));
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setPaintTicks(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("Slow"));
        labels.put(50, new JLabel("Medium"));
        labels.put(100, new JLabel("Fast"));
        speedSlider.setLabelTable(labels);

        controls.add(speedSlider);
        controls.add(genBtn);
        controls.add(sortBtn);
        controls.add(stopBtn);

        add(controls, BorderLayout.NORTH);
        add(arrayPanel, BorderLayout.CENTER);

        genBtn.addActionListener(e -> generateArray());
        sortBtn.addActionListener(e -> startSorting());
        stopBtn.addActionListener(e -> stopSorting());

        generateArray();
    }

    void generateArray() {
        stopSorting();
        int n = 120;
        int[] arr = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(500) + 10;
        arrayPanel.setArray(arr);
    }

    void startSorting() {
        stopSorting();
        stopFlag = false;

        Sort sorter = switch ((String) algoBox.getSelectedItem()) {
            case "Insertion" -> new InsertionSort();
            case "Selection" -> new SelectionSort();
            case "Merge" -> new MergeSort();
            default -> new BubbleSort();
        };

        int[] copy = Arrays.copyOf(arrayPanel.getArray(), arrayPanel.getArray().length);
        worker = new Thread(() -> sorter.sort(copy, arrayPanel::update, this::shouldStop, this::delay));
        worker.start();
    }

    private boolean shouldStop() {
        return stopFlag;
    }

    private void delay() {
        try {
            int s = speedSlider.getValue();
            long ms = 10 + (long) ((100 - s) * 2);
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    void stopSorting() {
        stopFlag = true;
        if (worker != null && worker.isAlive()) {
            worker.interrupt();
        }
    }

    public ArrayPanel getArrayPanel() {
        return arrayPanel;
    }

    public JComboBox<String> getAlgoBox() {
        return algoBox;
    }

    public JSlider getSpeedSlider() {
        return speedSlider;
    }

    public JButton getGenBtn() {
        return genBtn;
    }

    public JButton getSortBtn() {
        return sortBtn;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
