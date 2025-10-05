package org.example;

import java.io.*;
import java.util.*;

public class RoadSystem {
    protected Set<String> cities;
    protected List<Road> roads;
    private Map<String, List<Road>> mst;
    private final String MINSK = "Minsk";

    public RoadSystem() {
        cities = new HashSet<>();
        roads = new ArrayList<>();
        mst = new HashMap<>();
    }

    public void readRoadsFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length < 3) continue;

                String firstCity = parts[0];
                String secondCity = parts[1];
                int distance = Integer.parseInt(parts[2]);

                cities.add(firstCity);
                cities.add(secondCity);

                roads.add(new Road(firstCity, secondCity, distance));
            }
        }
    }

    public void buildMST() {
        if (!cities.contains(MINSK)) {
            throw new IllegalStateException("Minsk not found in the road system!");
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.distance));
        mst.clear();

        for (String city : cities) {
            mst.put(city, new ArrayList<>());
        }

        visited.add(MINSK);
        addEdgesToQueue(MINSK, visited, pq);

        while (!pq.isEmpty() && visited.size() < cities.size()) {
            Road road = pq.poll();

            String nextCity = null;
            if (visited.contains(road.firstCity) && !visited.contains(road.secondCity)) {
                nextCity = road.secondCity;
            } else if (visited.contains(road.secondCity) && !visited.contains(road.firstCity)) {
                nextCity = road.firstCity;
            }

            if (nextCity != null) {
                mst.get(road.firstCity).add(road);
                mst.get(road.secondCity).add(road);

                visited.add(nextCity);
                addEdgesToQueue(nextCity, visited, pq);
            }
        }
    }

    private void addEdgesToQueue(String city, Set<String> visited, PriorityQueue<Road> pq) {
        for (Road road : roads) {
            String neighbor = road.getNeighbor(city);
            if (neighbor != null && !visited.contains(neighbor)) {
                pq.offer(road);
            }
        }
    }

    public PathDistance findShortestPath(String targetCity) {
        if (!cities.contains(targetCity)) {
            throw new IllegalArgumentException("City not found: " + targetCity);
        }

        List<String> path = new ArrayList<>();
        int totalDistance = dfsFindPath(MINSK, targetCity, null, path, 0);

        if (totalDistance >= 0) {
            return new PathDistance(path, totalDistance);
        } else {
            throw new IllegalArgumentException("No way from Minsk to " + targetCity);
        }
    }

    private int dfsFindPath(String current, String target, String parent, List<String> path, int currentDistance) {
        path.add(current);

        if (current.equals(target)) {
            return currentDistance;
        }

        for (Road road : mst.get(current)) {
            String neighbor = road.getNeighbor(current);
            if (neighbor != null && !neighbor.equals(parent)) {
                int distance = dfsFindPath(neighbor, target, current, path, currentDistance + road.distance);
                if (distance >= 0) {
                    return distance;
                }
            }
        }

        path.removeLast();
        return -1;
    }

    public void saveMSTToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Set<String> printedRoads = new HashSet<>();

            for (List<Road> cityRoads : mst.values()) {
                for (Road road : cityRoads) {
                    String roadKey = generateRoadKey(road);
                    if (!printedRoads.contains(roadKey)) {
                        pw.println(road.firstCity + " " + road.secondCity + " " + road.distance);
                        printedRoads.add(roadKey);
                    }
                }
            }
        }
    }

    private String generateRoadKey(Road road) {
        return road.firstCity.compareTo(road.secondCity) < 0 ?
                road.firstCity + "-" + road.secondCity : road.secondCity + "-" + road.firstCity;
    }

    public void printMST() {
        System.out.println("Minimal Spanning Tree:");
        Set<String> printedRoads = new HashSet<>();

        for (List<Road> cityRoads : mst.values()) {
            for (Road road : cityRoads) {
                String roadKey = generateRoadKey(road);
                if (!printedRoads.contains(roadKey)) {
                    System.out.println(road);
                    printedRoads.add(roadKey);
                }
            }
        }
    }
}
