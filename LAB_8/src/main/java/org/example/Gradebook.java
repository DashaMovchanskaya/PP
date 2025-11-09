package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gradebook {
    private String lastname,name, patronymic;
    private int year;
    private int group;
    List<Session> sessions;

    public Gradebook() {
        this.sessions = new ArrayList<>();
    }

    public Gradebook(String l, String n, String p, int y, int gr) {
        this.lastname = l;
        this.name = n;
        this.patronymic = p;
        this.year = y;
        this.group = gr;
        this.sessions = new ArrayList<>();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Session {
        private int number;
        private List<Subject> subjects;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Subject {
            private String subject;
            private boolean exam;
            private int mark;

            public Subject() {}

            public Subject(String s, boolean e, int m) {
                this.subject = s;
                this.exam = e;
                this.mark = m;
            }

            public boolean isExcellent() {
                if ((mark > 8 && exam) || (mark == 1 && !exam)) {
                    return true;
                }
                return false;
            }

            public String getSubject() { return subject; }
            public boolean isExam() { return exam; }
            public int getMark() { return mark; }
        }

        public Session() {
            this.subjects = new ArrayList<>();
        }

        public Session(int n) {
            this.number = n;
            this.subjects = new ArrayList<>();
        }

        public void addSubject(String s, boolean e, int m) {
            Subject subject = new Subject(s, e, m);
            subjects.add(subject);
        }

        public List<Subject> getSubject() { return subjects; }
        public int getNumber() { return number; }

        public boolean isExcellent() {
            for (Subject s : subjects) {
                if (!s.isExcellent()) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addSession(int number) {
        Session session = new Session(number);
        sessions.add(session);
    }

    public Session getLastSession() {
        if (sessions.isEmpty()) {
            return null;
        }
        return sessions.get(sessions.size() - 1);
    }
    public List<Session> getSession() { return sessions; }
    public String getLastname() { return lastname; }
    public String getName() { return name; }
    public String getPatronymic() { return patronymic; }
    public int getYear() { return year; }
    public int getGroup() { return group; }

    public boolean isExcellent() {
        for (Session s : sessions) {
            if (!s.isExcellent()) {
                return false;
            }
        }
        return true;
    }
}
