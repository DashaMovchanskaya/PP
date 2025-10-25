package org.example;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class TreeManagerTest {
    private TreeManager treeManager;

    private void setUp() {
        treeManager = new TreeManager();
    }

    @Test
    public void testAddTree() {
        setUp();
        treeManager.addTree(new AppleTree("Test", 3, 90, 50));

        List<Trees> trees = treeManager.getTrees();
        assertEquals(1, trees.size());
        assertEquals("Test", trees.get(0).getName());
    }

    @Test
    public void testAddTreeWithParameters() {
        setUp();
        treeManager.addTree("Test Tree", "Яблоня", 4, 85, 55);

        List<Trees> trees = treeManager.getTrees();
        assertEquals(1, trees.size());
        assertEquals("Test Tree", trees.get(0).getName());
        assertEquals("Яблоня", trees.get(0).getType());
    }

    @Test
    public void testAddTreeWithInvalidParameters() {
        setUp();
        treeManager.addTree("", "Яблоня", 4, 85, 55);
        assertEquals(0, treeManager.getTrees().size());
    }

    @Test
    public void testSetGlobalStrategy() {
        setUp();
        treeManager.addTree(new AppleTree("Test1", 2, 90, 40));
        treeManager.addTree(new AppleTree("Test2", 8, 65, 45));

        TransplantStrategy strategy = new YoungTreeStrategy();
        treeManager.setGlobalStrategy(strategy);

        treeManager.checkAllTrees();
    }

    @Test
    public void testSetIndividualStrategy() {
        setUp();
        treeManager.addTree(new AppleTree("Specific", 3, 90, 50));
        treeManager.addTree(new AppleTree("Other", 10, 70, 60));

        TransplantStrategy strategy = new YoungTreeStrategy();
        treeManager.setIndividualStrategy("Specific", strategy);

        Trees specificTree = treeManager.getTrees().stream()
                .filter(t -> t.getName().equals("Specific"))
                .findFirst()
                .orElse(null);
        assertNotNull(specificTree);
        assertNotNull(specificTree.getStrategy());
    }

    @Test
    public void testSetIndividualStrategy_NonExistentTree() {
        setUp();
        treeManager.setIndividualStrategy("NonExistent", new YoungTreeStrategy());
        assertEquals(0, treeManager.getTrees().size());
    }

    @Test
    public void testLoadTreesFromFile_FileNotFound() {
        setUp();
        treeManager.loadTreesFromFile("non_existent_file.txt");

        assertNotNull(treeManager.getTrees());
    }

    @Test
    public void testSaveTreesToFile() {
        setUp();
        treeManager.addTree(new AppleTree("Test1", 3, 90, 50));
        treeManager.addTree(new CherryTree("Test2", 5, 85, 60));

        String filename = "test_output.txt";
        treeManager.saveTreesToFile(filename);

        File outputFile = new File(filename);
        boolean fileExists = outputFile.exists();

        if (fileExists) {
            outputFile.delete();
        }
    }

    @Test
    public void testClear() {
        setUp();
        treeManager.addTree(new AppleTree("Test", 3, 90, 50));
        assertEquals(1, treeManager.getTrees().size());

        treeManager.clear();
        assertEquals(0, treeManager.getTrees().size());
    }

    @Test
    public void testGetStatistics_Empty() {
        setUp();
        treeManager.getStatistics();
        assertEquals(0, treeManager.getTrees().size());
    }

    @Test
    public void testGetStatistics_WithTrees() {
        setUp();
        treeManager.addTree(new AppleTree("Test1", 3, 90, 50));
        treeManager.addTree(new CherryTree("Test2", 5, 85, 60));

        treeManager.getStatistics();
        assertEquals(2, treeManager.getTrees().size());
    }

    @Test
    public void testCheckAllTrees_Empty() {
        setUp();
        treeManager.checkAllTrees();
        assertEquals(0, treeManager.getTrees().size());
    }

    @Test
    public void testCheckAllTrees_WithTrees() {
        setUp();
        treeManager.addTree(new AppleTree("Test1", 3, 90, 50));
        treeManager.addTree(new CherryTree("Test2", 5, 85, 60));

        treeManager.checkAllTrees();
        assertEquals(2, treeManager.getTrees().size());
    }

    @Test
    public void testCreateTreeWithDifferentTypes() {
        setUp();
        treeManager.addTree("Apple", "Яблоня", 3, 90, 50);
        treeManager.addTree("Cherry", "Вишня", 4, 85, 55);
        treeManager.addTree("Pear", "Груша", 5, 80, 60);
        treeManager.addTree("Plum", "Слива", 6, 75, 65);

        List<Trees> trees = treeManager.getTrees();
        assertEquals(4, trees.size());
        assertEquals("Яблоня", trees.get(0).getType());
        assertEquals("Вишня", trees.get(1).getType());
        assertEquals("Груша", trees.get(2).getType());
        assertEquals("Слива", trees.get(3).getType());
    }

    @Test
    public void testCreateTreeWithEnglishTypeNames() {
        setUp();
        treeManager.addTree("Apple", "apple", 3, 90, 50);
        treeManager.addTree("Cherry", "cherry", 4, 85, 55);

        List<Trees> trees = treeManager.getTrees();
        assertEquals(2, trees.size());
        assertEquals("Яблоня", trees.get(0).getType());
        assertEquals("Вишня", trees.get(1).getType());
    }

    @Test
    public void testCreateTreeWithUnknownType() {
        setUp();
        treeManager.addTree("Unknown", "UnknownType", 3, 90, 50);
        assertEquals(0, treeManager.getTrees().size());
    }

    private Trees findTreeByName(String name) {
        return treeManager.getTrees().stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
