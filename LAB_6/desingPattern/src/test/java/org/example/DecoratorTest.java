package org.example;
import static org.junit.Assert.*;
import org.junit.Test;

public class DecoratorTest {
    @Test
    public void htmlDecoratorWrapsMessage() {
        Message msg = new HtmlDecorator(new SimpleMessage());
        assertEquals("<html>Привет</html>", msg.getContent());
    }
}
