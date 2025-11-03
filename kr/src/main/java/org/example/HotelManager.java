package org.example;

import java.io.*;
import java.util.*;

public class HotelManager {
    private List<Hotel> hotels = new ArrayList<>();

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String city;
            while ((city = reader.readLine()) != null) {
                String line = reader.readLine();
                if (line == null) break;

                city = city.trim();
                line = line.trim();

                int lastSpace = line.lastIndexOf(' ');
                if (lastSpace == -1) {
                    System.out.println("Пропущена некорректная строка: " + line);
                    continue;
                }

                String name = line.substring(0, lastSpace).trim();
                String starsStr = line.substring(lastSpace + 1).trim();

                try {
                    int stars = Integer.parseInt(starsStr);
                    hotels.add(new Hotel(city, name, stars));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка в строке: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public void printSortedByCityAndStars() {
        hotels.sort(Comparator
                .comparing(Hotel::getCity)
                .thenComparing(Hotel::getStars, Comparator.reverseOrder())
        );


        String currentCity = "";

        for (Hotel hotel : hotels) {
            if (!hotel.getCity().equals(currentCity)) {
                currentCity = hotel.getCity();
                System.out.println("Город: " + currentCity);
            }

            System.out.println("  " + hotel.getName() + " - " + hotel.getStars() + " звезды");
        }
    }

    public void findByCity(String cityName) {
        List<Hotel> result = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(cityName)) {
                result.add(hotel);
            }
        }

        if (result.isEmpty()) {
            System.out.println("Нет отелей в городе " + cityName);
        } else {
            for (Hotel hotel : result) {
                System.out.println(hotel);
            }
        }
    }

    public void findCitiesByHotelName(String hotelName) {
        Set<String> cities = new HashSet<>();

        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                cities.add(hotel.getCity());
            }
        }

        if (cities.isEmpty()) {
            System.out.println("Нет отелей с названием " + hotelName);
        } else {
            System.out.println("Города с отелем \"" + hotelName + "\":");
            for (String city : cities) {
                System.out.println(city);
            }
        }
    }
}

