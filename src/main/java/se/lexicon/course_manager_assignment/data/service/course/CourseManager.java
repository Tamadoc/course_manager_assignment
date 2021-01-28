package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {
        if (form != null) {
            Course course = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());

            return converters.courseToCourseView(course);
        }
        return null;
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        if (form != null) {
            Course course = courseDao.findById(form.getId());
            course.setCourseName(form.getCourseName());
            course.setStartDate(form.getStartDate());
            course.setWeekDuration(form.getWeekDuration());

            return converters.courseToCourseView(course);
        }
        return null;
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        Collection<Course> courses = courseDao.findByNameContains(courseName);
        if (courses != null) {
            return converters.coursesToCourseViews(courses);
        }
        return null;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        Collection<Course> courses = courseDao.findByDateBefore(end);
        if (courses != null) {
            return converters.coursesToCourseViews(courses);
        }
        return null;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        Collection<Course> courses = courseDao.findByDateAfter(start);
        if (courses != null) {
            return converters.coursesToCourseViews(courses);
        }
        return null;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        return course.enrollStudent(student);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        Course course = courseDao.findById(courseId);
        Student student = studentDao.findById(studentId);

        return course.unenrollStudent(student);
    }

    @Override
    public CourseView findById(int id) {
        Course course = courseDao.findById(id);
        if (course != null) {

            return converters.courseToCourseView(course);
        }
        return null;
    }

    @Override
    public List<CourseView> findAll() {
        Collection<Course> courses = courseDao.findAll();
        if (courses != null) {
            return converters.coursesToCourseViews(courses);
        }
        return null;
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        Collection<Course> courses = courseDao.findByStudentId(studentId);
        if (courses != null) {
            return converters.coursesToCourseViews(courses);
        }
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        Course course = courseDao.findById(id);
        return courseDao.removeCourse(course);
    }
}
