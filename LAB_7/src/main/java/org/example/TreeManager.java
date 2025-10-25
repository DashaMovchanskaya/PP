package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.Map;

public class TreeManager {
    private List<Trees> trees;
    private TransplantStrategy currentStrategy;

    public TreeManager() {
        this.trees = new ArrayList<>();
    }

    public void setGlobalStrategy(TransplantStrategy strategy) {
        this.currentStrategy = strategy;
        for (Trees tree : trees) {
            tree.setTransplantationStrategy(strategy);
        }
        System.out.println("Установлена глобальная стратегия: " + strategy.getClass().getSimpleName());
    }

    public void addTree(Trees tree) {
        if (currentStrategy != null) {
            tree.setTransplantationStrategy(currentStrategy);
        }
        trees.add(tree);
        System.out.println("Добавлено дерево: " + tree);
    }

    public void addTree(String name, String type, int age, double health, double fruitYield) {
        try {
            Trees tree = createTree(type, name, age, health, fruitYield);
            addTree(tree);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при создании дерева '" + name + "': " + e.getMessage());
        }
    }

    private Trees createTree(String type, String name, int age, double health, double fruitYield) {
        switch (type.toLowerCase()) {
            case "яблоня": case "apple":
                return new AppleTree(name, age, health, fruitYield);
            case "вишня": case "cherry":
                return new CherryTree(name, age, health, fruitYield);
            case "груша": case "pear":
                return new PearTree(name, age, health, fruitYield);
            case "слива": case "plum":
                return new PlumTree(name, age, health, fruitYield);
            default:
                throw new IllegalArgumentException("Неизвестный тип дерева: " + type);
        }
    }

    public void loadTreesFromFile(String filename) {
        int loadedCount = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                try {
                    Trees tree = parseTreeLine(line, lineNumber);
                    if (tree != null) {
                        addTree(tree);
                        loadedCount++;
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
                    errorCount++;
                }
            }

            System.out.printf("\nЗагрузка завершена. Успешно: %d, Ошибок: %d\n", loadedCount, errorCount);

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private Trees parseTreeLine(String line, int lineNumber) {
        String[] parts = line.split(";");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Неверный формат строки. Ожидается: тип;имя;возраст;здоровье;урожайность");
        }

        try {
            String type = parts[0].trim();
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            double health = Double.parseDouble(parts[3].trim());
            double fruitYield = Double.parseDouble(parts[4].trim());

            return createTree(type, name, age, health, fruitYield);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный числовой формат: " + e.getMessage());
        }
    }

    public void saveTreesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("# Файл данных деревьев");
            writer.println("# Формат: тип;имя;возраст;здоровье;урожайность");

            for (Trees tree : trees) {
                writer.printf("%s;%s;%d;%.1f;%.1f\n",
                        tree.getType(), tree.getName(), tree.getAge(),
                        tree.getHealth(), tree.getFruitYield());
            }

            System.out.println("Данные сохранены в файл: " + filename);

        } catch (IOException e) {
            System.err.println("Ошибка сохранения в файл: " + e.getMessage());
        }
    }

    public void checkAllTrees() {
        System.out.println("\nПРОВЕРКА ДЕРЕВЬЕВ В САДУ (" + trees.size() + " деревьев)");
        if (trees.isEmpty()) {
            System.out.println("В саду нет деревьев");
            return;
        }

        int needTransplant = 0;
        for (Trees tree : trees) {
            System.out.print(tree.getType() + " '" + tree.getName() +
                    "' (возраст: " + tree.getAge() +
                    " лет, здоровье: " + tree.getHealth() +
                    "%, урожайность: " + tree.getFruitYield() + "%): ");
            tree.checkTransplantation();

            if (tree.getStrategy() != null && tree.getStrategy().shouldTransplant(tree)) {
                needTransplant++;
            }
        }

        System.out.printf("\nИТОГ: %d из %d деревьев требуют пересадки\n", needTransplant, trees.size());
    }

    public void setIndividualStrategy(String treeName, TransplantStrategy strategy) {
        for (Trees tree : trees) {
            if (tree.getName().equalsIgnoreCase(treeName)) {
                tree.setTransplantationStrategy(strategy);
                System.out.println("Установлена индивидуальная стратегия для: " + tree.getName());
                return;
            }
        }
        System.err.println("Дерево с именем '" + treeName + "' не найдено");
    }

    public void getStatistics() {
        System.out.println("\n=== СТАТИСТИКА САДА ===");
        System.out.println("Всего деревьев: " + trees.size());

        int needTransplant = 0;
        double avgAge = 0;
        double avgHealth = 0;

        for (Trees tree : trees) {
            if (tree.getStrategy() != null && tree.getStrategy().shouldTransplant(tree)) {
                needTransplant++;
            }
            avgAge += tree.getAge();
            avgHealth += tree.getHealth();
        }

        System.out.println("Требуют пересадки: " + needTransplant);
        System.out.println("Не требуют: " + (trees.size() - needTransplant));
        System.out.printf("Средний возраст: %.1f лет\n", trees.isEmpty() ? 0 : avgAge / trees.size());
        System.out.printf("Среднее здоровье: %.1f%%\n", trees.isEmpty() ? 0 : avgHealth / trees.size());
    }

    public List<Trees> getTrees() {
        return new ArrayList<>(trees);
    }

    public void clear() {
        trees.clear();
        currentStrategy = null;
        System.out.println("Сад очищен");
    }
}
