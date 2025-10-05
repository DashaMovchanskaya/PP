package org.example;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.jar.*;
import java.util.zip.Deflater;

public class FolderToJar {

    public static void packFolderToJar(Path folderPath, String jarFileName) throws IOException {
        if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
            throw new IOException("Путь не существует или это не папка: " + folderPath);
        }

        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFileName))) {
            jos.setLevel(Deflater.DEFAULT_COMPRESSION);

            addDirectoryToJar(folderPath, folderPath, jos);
            System.out.println("Архивация завершена: " + jarFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка доступа: " + e.getMessage());
        }
    }

    private static void addDirectoryToJar(Path sourceDir, Path currentDir, JarOutputStream jos) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentDir)) {
            for (Path path : directoryStream) {
                String entryName = sourceDir.relativize(path).toString().replace("\\", "/");

                if (Files.isDirectory(path)) {
                    jos.putNextEntry(new JarEntry(entryName + "/"));
                    jos.closeEntry();
                    addDirectoryToJar(sourceDir, path, jos);
                } else {
                    System.out.println("Добавляется: " + entryName);
                    jos.putNextEntry(new JarEntry(entryName));

                    try (InputStream in = Files.newInputStream(path)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            jos.write(buffer, 0, len);
                        }
                    }

                    jos.closeEntry();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.print("Введите путь к папке: ");
        Scanner scanner = new Scanner(System.in);
        String inputPath = scanner.nextLine();

        Path folderPath = Paths.get(inputPath);

        if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
            System.out.println("Ошибка: путь не существует или это не папка.");
            scanner.close();
            return;
        }

        System.out.println("Путь корректен: " + folderPath.toAbsolutePath());
        System.out.print("Введите название папки архива: ");
        String fileName = scanner.nextLine();

        packFolderToJar(folderPath, fileName);
        scanner.close();
    }
}

