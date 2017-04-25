package services;

import model.dao.impl.StudentDAOImpl;
import model.entity.Student;
import model.dao.interfaces.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by ivans on 19/04/2017.
 */
@Service
public class StudentService implements StudentServiceInterface {
    public StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Collection<Student> getAllStudents() {
        return studentDAO.getAll();
    }
}


