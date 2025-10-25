package org.example;

public interface TransplantStrategy {
    boolean shouldTransplant(Trees tree);
    String getReason();
}

