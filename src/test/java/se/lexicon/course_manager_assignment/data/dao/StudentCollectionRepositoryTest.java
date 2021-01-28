package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;
    private Student student1, student2;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @BeforeEach
    void setUp() {
        student1 = testObject.createStudent("Teri", "email@email.com", "Address");
        student2 = testObject.createStudent("Sandstedt", "gmail@gmail.com", "Address");
        testObject.createStudent("Teri Sandstedt", "mail@eail.com", "Address");
        testObject.createStudent("Sandstedt Teri", "epost@epost.com", "Address");
    }

    @Test
    void createStudent() {
        Student expected = new Student("Sandstedt", "hotmail@gmail.com", "Address");
        Student actual = testObject.createStudent("Sandstedt", "hotmail@gmail.com", "Address");
        assertEquals(expected, actual);
    }

    @Test
    void findByEmailIgnoreCase() {
        Student expected = student2;
        Student actual = testObject.findByEmailIgnoreCase("gmail@gmail.com");
        assertEquals(expected, actual);
    }

    @Test
    void findByNameContains() {
        Collection<Student> students = testObject.findByNameContains("eri");

        // students.size() should be 3
        int expected = 3;
        int actual = students.size();
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Student expected = student1;
        Student actual = testObject.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Collection<Student> students = testObject.findAll();

        // students.size() should be 4
        int expected = 4;
        int actual = students.size();
        assertEquals(expected, actual);
    }

    @Test
    void removeStudent() {
        testObject.removeStudent(student1);

        // all students size() should be 3
        int expected = 3;
        int actual = testObject.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        testObject.clear();

        // all students size() should be 0
        int expected = 0;
        int actual = testObject.findAll().size();
        assertEquals(expected, actual);
    }

    // Existing AfterEach
    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
