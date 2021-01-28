package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;


public class Course {
    final int id;
    String courseName;
    LocalDate startDate;
    int weekDuration;
    Collection<Student> students = new HashSet<>();

    public Course() {
        id = CourseSequencer.nextCourseId();
    }

    public Course(String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        this();
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student newStudent) {
        if (newStudent == null){
            return false;
        }
        return students.add(newStudent);
    }

    public boolean unenrollStudent(Student oldStudent) {
        return students.remove(oldStudent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return weekDuration == course.weekDuration &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course Name: " + courseName + "\n" +
                "Start Date: " + startDate + "\n" +
                "Duration: " + weekDuration + " weeks\n" +
                "Students:\n" + students;
    }
}
