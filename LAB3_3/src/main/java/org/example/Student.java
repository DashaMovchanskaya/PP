package org.example;

import java.util.Objects;

public class Student {
    long num;
    String name;
    int group;
    double grade;

    public Student(long num, String name, int group, double grade) {
        this.num = num;
        this.name = name;
        this.group = group;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return num == s.num &&
                group == s.group &&
                Double.compare(s.grade, grade) == 0 &&
                Objects.equals(name, s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, group, grade);
    }

    public String toString() {
        return num + " " + name + " " + group + " " + grade;
    }

    public static Student fromString(String line) {
        String[] parts = line.trim().split("\\s+");
        return new Student(
                Long.parseLong(parts[0]),
                parts[1],
                Integer.parseInt(parts[2]),
                Double.parseDouble(parts[3])
        );
    }
}
