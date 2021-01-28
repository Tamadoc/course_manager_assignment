package se.lexicon.course_manager_assignment.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class CourseTest {

    private final Collection<Student> students = new HashSet<>();
    private Student student1;
    private Course course1;
    private Course course2;

    @BeforeEach
    void setUp() {
        student1 = new Student("Teri", "email@email.com", "Address");
        students.add(student1);

        course1 = new Course("Java", LocalDate.parse("2020-12-07"), 34, students);
        course2 = new Course("C#", LocalDate.parse("2020-01-02"), 52, students);
    }

    @Test
    void getId_idIncrementsWithEachNewClass() {
        // Course id should increment from 1 for the first course created.
        int expected = 2;
        int actual = course2.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getCourseName() {
        String expected = "Java";
        String actual = course1.getCourseName();
        assertEquals(expected, actual);
    }

    @Test
    void setCourseName() {
        course1.setCourseName("Java Basics");

        String expected = "Java Basics";
        String actual = course1.getCourseName();
        assertEquals(expected, actual);
    }

    @Test
    void getStartDate() {
        LocalDate expected = LocalDate.of(2020, 1, 2);
        LocalDate actual = course2.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    void setStartDate() {
        course2.setStartDate(LocalDate.parse("2021-02-03"));

        LocalDate expected = LocalDate.of(2021, 2, 3);
        LocalDate actual = course2.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    void getWeekDuration() {
        int expected = 34;
        int actual = course1.getWeekDuration();
        assertEquals(expected, actual);
    }

    @Test
    void setWeekDuration() {
        course1.setWeekDuration(30);

        int expected = 30;
        int actual = course1.getWeekDuration();
        assertEquals(expected, actual);
    }

    @Test
    void getStudents_CheckLengthOfCollection() {
        int expected = 1;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void setStudents_CheckLengthOfNewCollection() {
        Collection<Student> students2 = new ArrayList<>();
        students2.add(new Student());
        students2.add(new Student());

        course1.setStudents(students2);

        int expected = 2;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void enrollStudent_NullStudent() {
        Student student2 = null;
        course1.enrollStudent(student2);

        int expected = 1;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void enrollStudent_ExistingEmail() {
        Student student2 = new Student("Sandstedt", "email@email.com", "Address");
        course1.enrollStudent(student2);

        int expected = 1;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void enrollStudent_NewStudentDetails() {
        Student student2 = new Student();
        course1.enrollStudent(student2);

        int expected = 2;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void unenrollStudent_StudentExists() {
        course1.unenrollStudent(student1);

        int expected = 0;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void unenrollStudent_StudentDoesNotExist() {
        Student student2 = new Student("Sandstedt", "mail@mail.com", "Other Address");
        course1.unenrollStudent(student2);

        int expected = 1;
        int actual = course1.getStudents().size();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals_DifferentCoursesInSystem() {
        boolean assertion = course1.equals(course2);
        assertFalse(assertion);
    }

    @Test
    void testEquals_NewCourseWithExistingDetails() {
        Course course3 = new Course("Java", LocalDate.parse("2020-12-07"), 34, students);
        boolean assertion = course1.equals(course3);
        assertTrue(assertion);
    }

    @AfterEach
    void tearDown() {
        // Reset sequencers to zero
        CourseSequencer.setCourseSequencer(0);
        StudentSequencer.setStudentSequencer(0);
    }
}
