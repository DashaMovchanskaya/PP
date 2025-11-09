package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class AppTest 
{
    private Gradebook gradebook;
    private Gradebook.Session session;

    private void setUp() {
        gradebook = new Gradebook("Ivanov", "Ivan", "Ivanovich", 2, 5);
        gradebook.addSession(1);
        session = gradebook.getLastSession();
    }

    @Test
    public void testGradebookCreation() {
        setUp();
        assertEquals("Ivanov", gradebook.getLastname());
        assertEquals("Ivan", gradebook.getName());
        assertEquals("Ivanovich", gradebook.getPatronymic());
        assertEquals(2, gradebook.getYear());
        assertEquals(5, gradebook.getGroup());
        assertFalse(gradebook.getSession().isEmpty());
    }

    @Test
    public void testSessionCreation() {
        setUp();
        assertNotNull(session);
        assertEquals(1, session.getNumber());
        assertTrue(session.getSubject().isEmpty());
    }

    @Test
    public void testAddSubjectToSession() {
        setUp();
        session.addSubject("Mathematics", true, 9);
        session.addSubject("History", false, 1);

        List<Gradebook.Session.Subject> subjects = session.getSubject();
        assertEquals(2, subjects.size());
        assertEquals("Mathematics", subjects.get(0).getSubject());
        assertEquals("History", subjects.get(1).getSubject());
    }

    @Test
    public void testSubjectProperties() {
        setUp();
        session.addSubject("Physics", true, 10);
        Gradebook.Session.Subject subject = session.getSubject().get(0);

        assertEquals("Physics", subject.getSubject());
        assertTrue(subject.isExam());
        assertEquals(10, subject.getMark());
    }

    @Test
    public void testExcellentExamSubject() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 9);
        session.addSubject("Physics", true, 10);

        assertTrue(session.isExcellent());
    }

    @Test
    public void testNonExcellentExamSubject() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 8);
        session.addSubject("Physics", true, 10);

        assertFalse(session.isExcellent());
    }

    @Test
    public void testExcellentCreditSubject() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("History", false, 1);

        assertTrue(session.isExcellent());
    }

    @Test
    public void testNonExcellentCreditSubject() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("History", false, 0); // Несданный зачет

        assertFalse(session.isExcellent());
    }

    @Test
    public void testMixedExcellentSubjects() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 9);
        session.addSubject("Physics", true, 10);
        session.addSubject("History", false, 1);
        session.addSubject("PE", false, 1);

        assertTrue(session.isExcellent());
        assertTrue(gradebook.isExcellent());
    }

    @Test
    public void testStudentWithMultipleSessionsAllExcellent() {
        Gradebook gradebook = new Gradebook("Excellent", "Student", "Test", 2, 5);

        gradebook.addSession(1);
        Gradebook.Session session1 = gradebook.getLastSession();
        session1.addSubject("Math", true, 9);
        session1.addSubject("History", false, 1);

        gradebook.addSession(2);
        Gradebook.Session session2 = gradebook.getLastSession();
        session2.addSubject("Physics", true, 10);
        session2.addSubject("Philosophy", false, 1);

        assertTrue(gradebook.isExcellent());
    }

    @Test
    public void testStudentWithMultipleSessionsOneNonExcellent() {
        Gradebook gradebook = new Gradebook("NonExcellent", "Student", "Test", 2, 5);

        gradebook.addSession(1);
        Gradebook.Session session1 = gradebook.getLastSession();
        session1.addSubject("Math", true, 9);
        session1.addSubject("History", false, 1);

        gradebook.addSession(2);
        Gradebook.Session session2 = gradebook.getLastSession();
        session2.addSubject("Physics", true, 8); // Не отлично
        session2.addSubject("Philosophy", false, 1);

        assertFalse(gradebook.isExcellent());
    }

    @Test
    public void testEmptyGradebook() {
        Gradebook gradebook = new Gradebook("Empty", "Student", "Test", 1, 1);
        assertTrue(gradebook.isExcellent()); // Нет сессий - технически отличник
    }

    @Test
    public void testEmptySession() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        assertTrue(gradebook.isExcellent()); // Нет предметов - технически отлично
    }

    @Test
    public void testMinimumExcellentExamMark() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 9); // Минимальная отличная оценка
        assertTrue(session.isExcellent());
    }

    @Test
    public void testBelowExcellentExamMark() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 8); // Ниже отличной
        assertFalse(session.isExcellent());
    }

    @Test
    public void testMaximumExamMark() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        Gradebook.Session session = gradebook.getLastSession();

        session.addSubject("Math", true, 10); // Максимальная оценка
        assertTrue(session.isExcellent());
    }

    @Test
    public void testCompleteStudentScenario() {
        // Создание студента-отличника
        Gradebook excellentStudent = new Gradebook("Sidorova", "Anna", "Mikhailovna", 2, 7);

        excellentStudent.addSession(1);
        excellentStudent.getLastSession().addSubject("Math", true, 10);
        excellentStudent.getLastSession().addSubject("Physics", true, 9);
        excellentStudent.getLastSession().addSubject("History", false, 1);

        excellentStudent.addSession(2);
        excellentStudent.getLastSession().addSubject("Programming", true, 10);
        excellentStudent.getLastSession().addSubject("Databases", true, 9);
        excellentStudent.getLastSession().addSubject("Philosophy", false, 1);

        assertTrue(excellentStudent.isExcellent());
        assertEquals(2, excellentStudent.getSession().size());

        for (Gradebook.Session session : excellentStudent.getSession()) {
            assertTrue(session.isExcellent());
            for (Gradebook.Session.Subject subject : session.getSubject()) {
                assertTrue(subject.isExcellent());
            }
        }
    }

    @Test
    public void testRealWorldNonExcellentStudent() {
        Gradebook student = new Gradebook("Petrov", "Petr", "Petrovich", 2, 6);

        student.addSession(1);
        student.getLastSession().addSubject("Math", true, 8); // Не отлично
        student.getLastSession().addSubject("Physics", true, 9);
        student.getLastSession().addSubject("History", false, 1);

        assertFalse(student.isExcellent());
        assertFalse(student.getLastSession().isExcellent());

        Gradebook.Session.Subject math = student.getLastSession().getSubject().get(0);
        assertFalse(math.isExcellent());
    }

    @Test
    public void testMultipleSessionsWithDifferentNumbers() {
        Gradebook gradebook = new Gradebook("Test", "Student", "Test", 3, 10);

        for (int i = 1; i <= 5; i++) {
            gradebook.addSession(i);
            gradebook.getLastSession().addSubject("Subject" + i, true, 9);
        }

        List<Gradebook.Session> sessions = gradebook.getSession();
        assertEquals(5, sessions.size());
        assertEquals(1, sessions.get(0).getNumber());
        assertEquals(5, sessions.get(4).getNumber());
    }

    @Test
    public void testGetLastSessionOnEmptyGradebook() {
        Gradebook gradebook = new Gradebook("Empty", "Student", "Test", 1, 1);
        assertNull(gradebook.getLastSession());
    }

    @Test
    public void testSubjectWithZeroMarkForCredit() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);
        gradebook.getLastSession().addSubject("Failed", false, 0);

        Gradebook.Session.Subject subject = gradebook.getLastSession().getSubject().get(0);
        assertFalse(subject.isExcellent());
    }

    @Test
    public void testSubjectWithVariousMarks() {
        Gradebook gradebook = new Gradebook("Test", "Test", "Test", 1, 1);
        gradebook.addSession(1);

        // Тестируем разные оценки для экзаменов
        int[] examMarks = {1, 5, 8, 9, 10};
        boolean[] expectedResults = {false, false, false, true, true};

        for (int i = 0; i < examMarks.length; i++) {
            gradebook.getLastSession().addSubject("Subject" + i, true, examMarks[i]);
            Gradebook.Session.Subject subject = gradebook.getLastSession().getSubject().get(i);
            assertEquals("Mark " + examMarks[i] + " should be " + expectedResults[i], expectedResults[i],
                    subject.isExcellent());
        }
    }
}
