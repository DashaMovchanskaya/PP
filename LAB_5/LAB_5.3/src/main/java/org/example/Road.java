package org.example;

public class Road {
    String firstCity;
    String secondCity;
    int distance;

    public Road(String city1, String city2, int distance) {
        this.firstCity = city1;
        this.secondCity = city2;
        this.distance = distance;
    }

    public String getNeighbor(String city) {
        if (firstCity.equals(city)) return secondCity;
        if (secondCity.equals(city)) return firstCity;
        return null;
    }

    @Override
    public String toString() {
        return firstCity + " - " + secondCity + ": " + distance + " km";
    }
}
