package org.example;

public class CherryTree extends Trees {
    public CherryTree(String name, int age, double health, double fruitYield) {
        super(name, age, health, fruitYield);
    }

    @Override
    public String getType() {
        return "Вишня";
    }
}
