package org.example;

// Стратегия для старых деревьев
public class OldTreeStrategy implements TransplantStrategy {
    @Override
    public boolean shouldTransplant(Trees tree) {
        return tree.getAge() >= 15 && tree.getHealth() < 40;
    }

    @Override
    public String getReason() {
        return "Старые деревья пересаживаются только в случае плохого здоровья";
    }
}
