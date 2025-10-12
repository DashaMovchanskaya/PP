package org.example;

public class App {
    public static void main(String[] args) {
        // Builder
        User user = new User.Builder()
                .setName("Даша")
                .setAge(18)
                .setEmail("dasha@example.com")
                .build();
        System.out.println("User: " + user.getName() + ", " + user.getAge() + ", " + user.getEmail());

        // Decorator
        Message msg = new HtmlDecorator(new SimpleMessage());
        System.out.println("Decorated message: " + msg.getContent());

        // Singleton
        Settings s1 = Settings.getInstance();
        s1.setTheme("dark");
        Settings s2 = Settings.getInstance();
        System.out.println("Theme from s2: " + s2.getTheme());
    }
}

