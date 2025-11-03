package org.example;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        HotelManager manager = new HotelManager();
        manager.loadFromFile("hotel.txt");

        System.out.println("1. Сортировка по городам и звёздам:");
        manager.printSortedByCityAndStars();

        System.out.print("2. Введите город для поиска информации об отелях: ");
        String city = scanner.nextLine().trim();
        manager.findByCity(city);

        System.out.print("3. Введите название отеля для поиска информации по городам: ");
        String hotel = scanner.nextLine().trim();
        manager.findCitiesByHotelName(hotel);
    }
}
