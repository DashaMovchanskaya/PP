package org.example;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest 
{
    @Test
    public void basic() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test1.txt");
        roadSystem.buildMST();
        PathDistance distance = roadSystem.findShortestPath("Vitebsk");

        assertTrue(distance != null);
        assertTrue(distance.totalDistance > 0);
    }

    @Test
    public void noCity() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test2.txt");
        roadSystem.buildMST();
        try {
            roadSystem.findShortestPath("Vitebsk");
            fail();
        } catch (IllegalArgumentException e) {
            // Всё хорошо
        }
    }

    @Test
    public void singleCity() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test3.txt");
        roadSystem.buildMST();
        PathDistance distance = roadSystem.findShortestPath("Brest");

        assertNotNull(distance);
        assertEquals(150, distance.totalDistance); // Расстояние до самого себя = 0
        assertEquals(List.of("Minsk", "Brest"), distance.path);
    }

    @Test
    public void pathToSelf() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test1.txt");
        roadSystem.buildMST();
        PathDistance distance = roadSystem.findShortestPath("Minsk");

        assertNotNull(distance);
        assertEquals(0, distance.totalDistance);
        assertEquals(List.of("Minsk"), distance.path);
    }

    @Test
    public void cyclicGraph() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test4.txt");
        roadSystem.buildMST();
        PathDistance distance = roadSystem.findShortestPath("Brest");

        assertNotNull(distance);
        assertEquals(350, distance.totalDistance);
        assertEquals(List.of("Minsk", "Gomel", "Brest"), distance.path);
    }

    @Test
    public void disconnectedGraph() throws IOException {
        RoadSystem roadSystem = new RoadSystem();
        roadSystem.readRoadsFromFile("test5.txt");
        roadSystem.buildMST();

        try {
            roadSystem.findShortestPath("Polotsk");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Polotsk"));
        } catch (Exception e) {
            fail(e.getClass().getSimpleName());
        }
    }
}
