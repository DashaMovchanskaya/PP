package org.example;

// Стратегия для зрелых плодоносящих деревьев
public class MatureFruitingStrategy implements TransplantStrategy {
    @Override
    public boolean shouldTransplant(Trees tree) {
        return tree.getAge() > 5 && tree.getAge() < 15 &&
                tree.getFruitYield() < 50 && tree.getHealth() > 60;
    }

    @Override
    public String getReason() {
        return "Пересадка может улучшить плодоношение зрелых деревьев";
    }
}
