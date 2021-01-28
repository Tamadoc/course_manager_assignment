package se.lexicon.course_manager_assignment.data.dao;

import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses = new HashSet<>();

    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Collection<Student> students = new HashSet<>();
        Course course = new Course(courseName, startDate, weekDuration, students);
        courses.add(course);

        return course;
    }

    @Override
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> results = new HashSet<>();

        for (Course course : courses) {
            if (course.getCourseName().contains(name)) {
                results.add(course);
            }
        }

        return results;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> results = new HashSet<>();

        for (Course course : courses) {
            if (course.getStartDate().isBefore(end)) {
                results.add(course);
            }
        }

        return results;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> results = new HashSet<>();

        for (Course course : courses) {
            if (course.getStartDate().isAfter(start)) {
                results.add(course);
            }
        }

        return results;
    }

    @Override
    public Collection<Course> findAll() {
        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> results = new HashSet<>();

        for (Course course : courses) {
            Collection<Student> students = course.getStudents();

            for (Student student :students) {
                if (student.getId() == studentId) {
                    results.add(course);
                }
            }
        }

        return results;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
