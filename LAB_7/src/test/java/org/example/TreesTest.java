package org.example;
import static org.junit.Assert.*;

import org.junit.Test;

public class TreesTest {
    private AppleTree appleTree;

    private void setUp() {
        appleTree = new AppleTree("Test Apple", 5, 80, 60);
    }

    @Test
    public void testTreeCreation() {
        setUp();
        assertEquals("Test Apple", appleTree.getName());
        assertEquals(5, appleTree.getAge());
        assertEquals(80.0, appleTree.getHealth(), 0.001);
        assertEquals(60.0, appleTree.getFruitYield(), 0.001);
        assertEquals("Яблоня", appleTree.getType());
    }

    @Test
    public void testTreeCreation_InvalidName() {
        try {
            new AppleTree("", 5, 80, 60);
            fail("Ожидалось IllegalArgumentException для пустого имени");
        } catch (IllegalArgumentException e) {
            // Проверяем сообщение исключения
            assertTrue(e.getMessage().contains("Имя дерева не может быть пустым") ||
                    e.getMessage().contains("имя"));
        }
    }

    @Test
    public void testTreeCreation_InvalidAge() {
        try {
            new AppleTree("Test", -1, 80, 60);
            fail("Ожидалось IllegalArgumentException для отрицательного возраста");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Возраст не может быть отрицательным") ||
                    e.getMessage().contains("возраст"));
        }
    }

    @Test
    public void testTreeCreation_InvalidHealth() {
        try {
            new AppleTree("Test", 5, 150, 60);
            fail("Ожидалось IllegalArgumentException для здоровья > 100");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Здоровье должно быть в диапазоне 0-100") ||
                    e.getMessage().contains("диапазон"));
        }
    }

    @Test
    public void testSetTransplantationStrategy() {
        setUp();
        TransplantStrategy strategy = new YoungTreeStrategy();
        appleTree.setTransplantationStrategy(strategy);

        // Проверяем, что стратегия установлена (косвенно через поведение)
        appleTree.checkTransplantation(); // Не должно бросать исключение
    }

    @Test
    public void testTreeTypes() {
        setUp();
        assertEquals("Яблоня", new AppleTree("A", 1, 100, 100).getType());
        assertEquals("Вишня", new CherryTree("C", 1, 100, 100).getType());
        assertEquals("Груша", new PearTree("P", 1, 100, 100).getType());
        assertEquals("Слива", new PlumTree("S", 1, 100, 100).getType());
    }

    @Test
    public void testToString() {
        setUp();
        String result = appleTree.toString();
        assertTrue(result.contains("Яблоня"));
        assertTrue(result.contains("Test Apple"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("80"));
        assertTrue(result.contains("60"));
    }
}
