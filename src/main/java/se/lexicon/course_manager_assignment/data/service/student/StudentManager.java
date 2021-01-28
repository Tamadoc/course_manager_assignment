package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        if (form != null) {
            Student student = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());

            return converters.studentToStudentView(student);
        }
        return null;
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        if (form != null) {
            Student student = studentDao.findById(form.getId());
            student.setName(form.getName());
            student.setEmail(form.getEmail());
            student.setAddress(form.getAddress());

            return converters.studentToStudentView(student);
        }
        return null;
    }

    @Override
    public StudentView findById(int id) {
        Student student = studentDao.findById(id);
        if (student != null) {
            return converters.studentToStudentView(student);
        }
        return null;
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);
        if (student != null) {
            return converters.studentToStudentView(student);
        }
        return null;
    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection<Student> students = studentDao.findByNameContains(name);
        if (students != null) {
            return converters.studentsToStudentViews(students);
        }
        return null;
    }

    @Override
    public List<StudentView> findAll() {
        Collection<Student> students = studentDao.findAll();
        if (students != null) {
            return converters.studentsToStudentViews(students);
        }
        return null;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = studentDao.findById(id);
        return studentDao.removeStudent(student);
    }
}
