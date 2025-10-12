package org.example;

import java.text.*;
import java.util.*;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        try{
            // 1. Работа с Locale
            Locale current = Locale.getDefault();
            Locale russian = new Locale("ru", "RU");
            Locale german = Locale.GERMANY;

            System.out.println("\nИнформация о локалях:");
            System.out.println("RU → Код страны: " + russian.getCountry() + ", Название: " + russian.getDisplayCountry());
            System.out.println("RU → Код языка: " + russian.getLanguage() + ", Название: " + russian.getDisplayLanguage());

            System.out.println("DE → Код страны: " + german.getCountry() + ", Название: " + german.getDisplayCountry());
            System.out.println("DE → Код языка: " + german.getLanguage() + ", Название: " + german.getDisplayLanguage());

            System.out.println("Текущая локаль: " + current);
            System.out.println("Страна: " + current.getDisplayCountry());
            System.out.println("Язык: " + current.getDisplayLanguage());

            // 2. Работа с ResourceBundle
            ResourceBundle rb = ResourceBundle.getBundle("text", russian);
            System.out.println("\nСтрока из ресурсов (str1): " + rb.getString("str1"));

            ResourceBundle rbDe = ResourceBundle.getBundle("text", Locale.GERMANY);
            System.out.println("Строка из ресурсов (DE, str2): " + rbDe.getString("str2"));

            // 3. Интернационализация чисел
            double number = 1234567.89;

            NumberFormat nfRu = NumberFormat.getInstance(russian);
            NumberFormat nfDe = NumberFormat.getInstance(german);
            NumberFormat nfUs = NumberFormat.getInstance(Locale.US);

            System.out.println("\nФормат RU: " + nfRu.format(number));
            System.out.println("Формат DE: " + nfDe.format(number));
            System.out.println("Формат US: " + nfUs.format(number));

            // 4. Интернационализация дат
            Date now = new Date();

            DateFormat dfRu = DateFormat.getDateInstance(DateFormat.FULL, russian);
            DateFormat dfUs = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
            DateFormat dfTimeDe = DateFormat.getTimeInstance(DateFormat.SHORT, german);
            DateFormat dfTimeRu = DateFormat.getTimeInstance(DateFormat.SHORT, russian);

            dfTimeRu.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
            dfTimeDe.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));

            System.out.println("\nДата (RU): " + dfRu.format(now));
            System.out.println("Дата (US): " + dfUs.format(now));
            System.out.println("Время (DE): " + dfTimeDe.format(now));
            System.out.println("Время (RU): " + dfTimeRu.format(now));
        }
        catch (MissingResourceException e) {
            System.out.println("Файл ресурсов не найден: " + e.getMessage());
        }
    }
}

