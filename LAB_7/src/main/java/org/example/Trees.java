package org.example;

// Базовый класс дерева
public abstract class Trees {
    protected String name;
    protected int age;
    protected double health;
    protected double fruitYield;
    protected TransplantStrategy strategy;

    public Trees(String name, int age, double health, double fruitYield) {
        validateName(name);
        validateAge(age);
        validatePercentage(health, "Здоровье");
        validatePercentage(fruitYield, "Урожайность");

        this.name = name;
        this.age = age;
        this.health = health;
        this.fruitYield = fruitYield;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя дерева не может быть пустым");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Имя дерева слишком длинное (макс. 50 символов)");
        }
    }

    private void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным: " + age);
        }
        if (age > 200) {
            throw new IllegalArgumentException("Возраст слишком большой (макс. 200 лет): " + age);
        }
    }

    private void validatePercentage(double value, String fieldName) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(fieldName + " должно быть в диапазоне 0-100: " + value);
        }
    }

    public void setTransplantationStrategy(TransplantStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkTransplantation() {
        if (strategy == null) {
            System.out.println(name + ": Стратегия пересадки не установлена");
            return;
        }

        if (strategy.shouldTransplant(this)) {
            System.out.println("✓ " + name + " требует пересадки. " + strategy.getReason());
        } else {
            System.out.println("✗ " + name + " не требует пересадки");
        }
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getHealth() { return health; }
    public double getFruitYield() { return fruitYield; }
    public TransplantStrategy getStrategy() { return strategy; }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s '%s' (возраст: %d, здоровье: %.1f%%, урожайность: %.1f%%)",
                getType(), name, age, health, fruitYield);
    }
}


