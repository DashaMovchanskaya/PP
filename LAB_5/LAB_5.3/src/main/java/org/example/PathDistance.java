package org.example;

import java.util.*;

public class PathDistance {
    public final List<String> path;
    public final int totalDistance;

    public PathDistance(List<String> path, int totalDistance) {
        this.path = new ArrayList<>(path);
        this.totalDistance = totalDistance;
    }
}
