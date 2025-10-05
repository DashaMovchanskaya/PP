package org.example;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.jar.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void  testBasicFolderPacking() throws IOException {
        Path folder = Files.createTempDirectory("basic");
        Files.writeString(folder.resolve("file.txt"), "Hello");

        Path jarPath = folder.resolve("basic.jar");
        FolderToJar.packFolderToJar(folder, jarPath.toString());

        assertTrue(Files.exists(jarPath));
        try (JarFile jar = new JarFile(jarPath.toFile())) {
            assertNotNull(jar.getEntry("file.txt"));
        }
    }

    @Test
    public void emptyJarFromEmptyFolder() throws IOException {
        Path folder = Files.createTempDirectory("empty");
        Path jarPath = Files.createTempFile("output", ".jar");

        FolderToJar.packFolderToJar(folder, jarPath.toString());

        JarFile jar = new JarFile(jarPath.toFile());
        boolean hasFiles = jar.stream().anyMatch(entry -> !entry.getName().endsWith("/") && !entry.isDirectory());
        assertFalse(hasFiles);
    }


    @Test
    public void nestedFoldersPacking() throws IOException {
        Path root = Files.createTempDirectory("nested");
        Path sub = Files.createDirectory(root.resolve("sub"));
        Files.writeString(sub.resolve("deep.txt"), "Nested");

        Path jarPath = root.resolve("nested.jar");
        FolderToJar.packFolderToJar(root, jarPath.toString());

        try (JarFile jar = new JarFile(jarPath.toFile())) {
            assertNotNull(jar.getEntry("sub/"));
            assertNotNull(jar.getEntry("sub/deep.txt"));
        }
    }

    @Test
    public void emptyFilePacking() throws IOException {
        Path folder = Files.createTempDirectory("zero");
        Files.createFile(folder.resolve("empty.txt"));

        Path jarPath = folder.resolve("zero.jar");
        FolderToJar.packFolderToJar(folder, jarPath.toString());

        try (JarFile jar = new JarFile(jarPath.toFile())) {
            JarEntry entry = (JarEntry) jar.getEntry("empty.txt");
            assertNotNull(entry);
            assertEquals(0, entry.getSize());
        }
    }

    @Test
    public void invalidPathHandling() {
        Path invalidPath = Paths.get("Z:/nonexistent/folder");
        try {
            FolderToJar.packFolderToJar(invalidPath, "fail.jar");
            Assert.fail("Ожидалось исключение IOException, но оно не было выброшено");
        } catch (IOException e) {
            // Всё хорошо — это то, что мы ожидали
        } catch (Throwable t) {
            throw new AssertionError("Ожидалось IOException, но было: " + t.getClass().getName());
        }
    }

}
