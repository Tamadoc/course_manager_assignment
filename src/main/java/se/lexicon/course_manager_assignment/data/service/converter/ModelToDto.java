package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        if (student != null) {
            return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
        }

        return null;
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        if (course != null) {
            List<StudentView> students = studentsToStudentViews(course.getStudents());

            return new CourseView(course.getId(), course.getCourseName(), course.getStartDate(),
                    course.getWeekDuration(), students);
        }

        return null;
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        if (courses != null) {
            List<CourseView> courseViews = new ArrayList<>();

            for (Course course : courses) {
                CourseView view = courseToCourseView(course);
                courseViews.add(view);
            }
            return courseViews;
        }
        return null;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        if (students != null) {
            List<StudentView> studentViews = new ArrayList<>();
            StudentView view;

            for (Student student : students) {
                view = studentToStudentView(student);
                studentViews.add(view);
            }
            return studentViews;
        }

        return null;
    }
}
