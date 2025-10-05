package org.example;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RoadSystem roadSystem = new RoadSystem();

        try {
            roadSystem.readRoadsFromFile("roads.txt");
            System.out.println("Loaded " + roadSystem.cities.size() + " cities and " + roadSystem.roads.size() + " roads");

            roadSystem.buildMST();
            roadSystem.saveMSTToFile("mst_tree.txt");
            System.out.println("MST saved to mst_tree.txt");

            roadSystem.printMST();

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter target city: ");
            String targetCity = scanner.nextLine().trim();

            PathDistance result = roadSystem.findShortestPath(targetCity);

            if (result != null) {
                System.out.println("\n✓ Path from Minsk to " + targetCity + ":");
                System.out.println("Route: " + String.join(" → ", result.path));
                System.out.println("Total distance: " + result.totalDistance + " km");
            } else {
                System.out.println("✗ Path to " + targetCity + " not found!");
            }

            scanner.close();

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
