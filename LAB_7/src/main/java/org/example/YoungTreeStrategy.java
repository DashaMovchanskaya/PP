package org.example;

public class YoungTreeStrategy implements TransplantStrategy {
    @Override
    public boolean shouldTransplant(Trees tree) {
        return tree.getAge() <= 3 && tree.getHealth() > 70;
    }

    @Override
    public String getReason() {
        return "Молодые деревья лучше адаптируются к новой почве";
    }
}
