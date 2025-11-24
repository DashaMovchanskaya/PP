package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class App extends JFrame implements ActionListener {
    private JTextField input = new JTextField();

    public App() {
        setTitle("Калькулятор");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        input.setEditable(false);
        input.setFont(new Font("Arial", Font.BOLD, 24));
        add(input, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4));
        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "C","←","(",")"
        };

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("0123456789.+-*/()".contains(cmd)) {
            input.setText(input.getText() + cmd);
        } else if ("=".equals(cmd)) {
            try {
                double result = evaluateExpression(input.getText());
                input.setText(String.valueOf(result));
            } catch (Exception ex) {
                input.setText("Ошибка");
            }
        } else if ("C".equals(cmd)) {
            input.setText("");
        } else if ("←".equals(cmd)) {
            String text = input.getText();
            if (!text.isEmpty()) {
                input.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    private double evaluateExpression(String expr) {
        List<String> tokens = tokenize(expr);
        List<String> rpn = toRPN(tokens);
        return evalRPN(rpn);
    }

    private List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            } else {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        if (num.length() > 0) tokens.add(num.toString());
        return tokens;
    }

    private List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        Map<String, Integer> prec = Map.of("+",1,"-",1,"*",2,"/",2);

        for (String t : tokens) {
            if (t.matches("\\d+(\\.\\d+)?")) {
                output.add(t);
            } else if (prec.containsKey(t)) {
                while (!stack.isEmpty() && prec.containsKey(stack.peek()) &&
                        prec.get(stack.peek()) >= prec.get(t)) {
                    output.add(stack.pop());
                }
                stack.push(t);
            } else if (t.equals("(")) {
                stack.push(t);
            } else if (t.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) output.add(stack.pop());
        return output;
    }

    private double evalRPN(List<String> rpn) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String t : rpn) {
            if (t.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(t));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (t) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(b == 0 ? Double.NaN : a / b); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
