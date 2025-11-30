package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    @Timeout(10)
    void testAppCreation() throws InterruptedException, InvocationTargetException {
        AtomicBoolean appCreated = new AtomicBoolean(false);

        SwingUtilities.invokeAndWait(() -> {
            App app = new App();
            assertNotNull(app);
            appCreated.set(true);
        });

        assertTrue(appCreated.get());
    }

    @Test
    void testArrayGeneration() {
        App app = new App();

        int[] initialArray = app.getArrayPanel().getArray();
        assertNotNull(initialArray);
        assertTrue(initialArray.length > 0);

        app.generateArray();
        int[] newArray = app.getArrayPanel().getArray();
        assertNotNull(newArray);
        assertEquals(initialArray.length, newArray.length);

        boolean arraysDifferent = false;
        for (int i = 0; i < initialArray.length; i++) {
            if (initialArray[i] != newArray[i]) {
                arraysDifferent = true;
                break;
            }
        }
        assertTrue(arraysDifferent, "Generated arrays should be different");
    }

    @Test
    @Timeout(10)
    void testSortAlgorithms() throws InterruptedException {
        App app = new App();

        String[] algorithms = {"Bubble", "Insertion", "Selection", "Merge"};

        for (String algo : algorithms) {
            app.getAlgoBox().setSelectedItem(algo);
            assertEquals(algo, app.getAlgoBox().getSelectedItem());

            app.startSorting();
            Thread.sleep(100);
            app.stopSorting();

            assertNotNull(app.getArrayPanel().getArray());
        }
    }

    @Test
    void testSpeedSlider() {
        App app = new App();

        JSlider slider = app.getSpeedSlider();
        assertNotNull(slider);

        assertEquals(0, slider.getMinimum());
        assertEquals(100, slider.getMaximum());
        assertEquals(50, slider.getValue());

        slider.setValue(75);
        assertEquals(75, slider.getValue());
    }
}