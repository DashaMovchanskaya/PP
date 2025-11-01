package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    private static List<Gradebook> gradebooks = new ArrayList<>();
    public static void main( String[] args )
    {
        readFromFile("input.txt");
        writeExcellentStudentsToFile("output.txt");

        System.out.println("\nВсе зачетные книжки обработаны. Результаты записаны в файл output.txt");
    }

    public static void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Gradebook gradebook = null;
            Gradebook.Session session = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("Студент:")) {
                    String[] parts = line.split(":");
                    String studentData = parts[1].trim();
                    String[] studentParts = studentData.split(" ");

                    String lastname = studentParts[0];
                    String name = studentParts[1];
                    String patronymic = studentParts[2];
                    int year = Integer.parseInt(studentParts[3]);
                    int group = Integer.parseInt(studentParts[4]);

                    gradebook = new Gradebook(lastname, name, patronymic, year, group);
                    gradebooks.add(gradebook);

                } else if (line.startsWith("Сессия")) {
                    // Формат: Session 1
                    int sessionNumber = Integer.parseInt(line.split(" ")[1]);
                    gradebook.addSession(sessionNumber);
                    session = gradebook.getLastSession();

                } else if (line.contains(":")) {
                    if (session != null) {
                        String[] parts = line.split(":");
                        String subjectName = parts[0].trim();
                        String[] typeMark = parts[1].trim().split(" ");
                        String type = typeMark[0];
                        int mark = Integer.parseInt(typeMark[1]);
                        boolean isExam = type.equals("экзамен");

                        session.addSubject(subjectName, isExam, mark);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошбика чтения файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка чтения данных из файла: " + e.getMessage());
        }
    }

    public static void writeExcellentStudentsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Отличники:");

            boolean foundExcellent = false;

            for (Gradebook gradebook : gradebooks) {
                if (gradebook.isExcellent()) {
                    foundExcellent = true;
                    writer.println("Студент: " + gradebook.getLastname() + " " +
                            gradebook.getName() + " " + gradebook.getPatronymic());
                    writer.println("Курс: " + gradebook.getYear());
                    writer.println("Группа: " + gradebook.getGroup());

                    for (Gradebook.Session session : gradebook.getSession()) {
                        writer.println("Сессия " + session.getNumber() + ":");

                        for (Gradebook.Session.Subject subject : session.getSubject()) {
                            writer.print(subject.getSubject());
                            if (subject.isExam()) {
                                writer.print(" (экзамен): " + subject.getMark());
                            }
                            writer.println();
                        }
                    }
                    writer.println();
                }
            }

            if (!foundExcellent) {
                writer.println("\nОтличников не найдено.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
