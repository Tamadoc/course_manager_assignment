package se.lexicon.course_manager_assignment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

public class StudentTest {

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student("Teri", "email@email.com", "Street address, Town");
        student2 = new Student("Sandstedt", "gmail@gmail.com", "Street address, Other Town");
    }

    @Test
    void getId_idIncrementsWithEachNewClass() {
        // Student id should increment from 1 for the first student created.
        int expected = 2;
        int actual = student2.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getName() {
        String expected = "Teri";
        String actual = student1.getName();
        assertEquals(expected, actual);
    }

    @Test
    void setName() {
        student1.setName("Teresa");

        String expected = "Teresa";
        String actual = student1.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        String expected = "email@email.com";
        String actual = student1.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void setEmail() {
        student1.setEmail("mail@mail.com");

        String expected = "mail@mail.com";
        String actual = student1.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void getAddress() {
        String expected = "Street address, Town";
        String actual = student1.getAddress();
        assertEquals(expected, actual);
    }

    @Test
    void setAddress() {
        student1.setAddress("Other Street, Town");

        String expected = "Other Street, Town";
        String actual = student1.getAddress();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals_DifferentStudentsInSystem() {
        boolean assertion = student1.equals(student2);
        assertFalse(assertion);
    }

    @Test
    void testEquals_NewStudentWithExistingEmail() {
        Student student3 = new Student("Teri", "email@email.com", "Street address, Town");
        boolean assertion = student1.equals(student3);
        assertTrue(assertion);
    }

    @Test
    void testToString() {
        String expected = "Name: Teri\nEmail: email@email.com\nAddress: Street address, Town";
        String actual = student1.toString();
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        // Reset sequencer to zero
        StudentSequencer.setStudentSequencer(0);
    }
}
