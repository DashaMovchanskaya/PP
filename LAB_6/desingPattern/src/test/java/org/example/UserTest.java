package org.example;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {
    @Test
    public void builderCreatesCorrectUser() {
        User user = new User.Builder()
                .setName("Даша")
                .setAge(18)
                .setEmail("dasha@example.com")
                .build();

        assertEquals("Даша", user.getName());
        assertEquals(18, user.getAge());
        assertEquals("dasha@example.com", user.getEmail());
    }
}
