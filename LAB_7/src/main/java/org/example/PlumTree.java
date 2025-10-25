package org.example;

public class PlumTree extends Trees {
    public PlumTree(String name, int age, double health, double fruitYield) {
        super(name, age, health, fruitYield);
    }

    @Override
    public String getType() {
        return "Слива";
    }
}
