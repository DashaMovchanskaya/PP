package org.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class SingletonTest {
    @Test
    public void singletonReturnsSameInstance() {
        Settings s1 = Settings.getInstance();
        Settings s2 = Settings.getInstance();

        s1.setTheme("dark");
        assertEquals("dark", s2.getTheme());
        assertSame(s1, s2);
    }
}
