package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;
    private Course course1, course2;
    private final Collection<Student> students = new HashSet<>();

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @BeforeEach
    void setUp() {
        course1 = testObject.createCourse("Java Beginners", LocalDate.parse("2021-01-01"), 50);
        course2 = testObject.createCourse("Java Intermediate", LocalDate.parse("2021-02-01"), 50);
        testObject.createCourse("Java Advanced", LocalDate.parse("2021-03-01"), 50);
        testObject.createCourse("C# Beginners", LocalDate.parse("2021-04-01"), 50);
    }

    @Test
    void createCourse() {
        Course expected = new Course("C#", LocalDate.parse("2021-02-01"), 48, students);
        Course actual = testObject.createCourse("C#", LocalDate.parse("2021-02-01"), 48);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        Course expected = course1;
        Course actual = testObject.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByNameContains() {
        Collection<Course> courses = testObject.findByNameContains("Beginners");

        // courses.size() should be 2
        int expected = 2;
        int actual = courses.size();
        assertEquals(expected, actual);
    }

    @Test
    void findByDateBefore() {
        LocalDate date = LocalDate.parse("2021-03-31");
        Collection<Course> courses = testObject.findByDateBefore(date);

        // courses.size() should be 3
        int expected = 3;
        int actual = courses.size();
        assertEquals(expected, actual);
    }

    @Test
    void findByDateAfter() {
        LocalDate date = LocalDate.parse("2021-03-31");
        Collection<Course> courses = testObject.findByDateAfter(date);

        // courses.size() should be 1
        int expected = 1;
        int actual = courses.size();
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Collection<Course> courses = testObject.findAll();

        // courses.size() should be 4
        int expected = 4;
        int actual = courses.size();
        assertEquals(expected, actual);
    }

    @Test
    void findByStudentId() {
        Student student = new Student("Teri", "mail@mail.com", "Address");
        course1.enrollStudent(student);
        course2.enrollStudent(student);
        Collection<Course> courses = testObject.findByStudentId(1);

        // courses.size() should be 2
        int expected = 2;
        int actual = courses.size();
        assertEquals(expected, actual);
    }

    @Test
    void removeCourse() {
        testObject.removeCourse(course1);

        // all courses size() should be 3
        int expected = 3;
        int actual = testObject.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        testObject.clear();

        // all courses size() should be 0
        int expected = 0;
        int actual = testObject.findAll().size();
        assertEquals(expected, actual);
    }

    // Existing tearDown()
    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
