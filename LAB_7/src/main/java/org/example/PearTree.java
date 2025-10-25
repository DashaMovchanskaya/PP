package org.example;

public class PearTree extends Trees {
    public PearTree(String name, int age, double health, double fruitYield) {
        super(name, age, health, fruitYield);
    }

    @Override
    public String getType() {
        return "Груша";
    }
}
