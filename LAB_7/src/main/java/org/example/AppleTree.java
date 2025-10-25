package org.example;

public class AppleTree extends Trees {
    public AppleTree(String name, int age, double health, double fruitYield) {
        super(name, age, health, fruitYield);
    }

    @Override
    public String getType() {
        return "Яблоня";
    }
}
