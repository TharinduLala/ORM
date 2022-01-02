package business.custom.impl;

import business.custom.StudentsBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDto;
import entity.Student;

import java.util.ArrayList;

public class StudentsBOImpl implements StudentsBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public StudentDto getStudent(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        StudentDto studentDto = new StudentDto();
        return studentDto;
    }

    @Override
    public ArrayList<StudentDto> getAllStudents() throws Exception {
        return null;
    }

    @Override
    public boolean addNewStudent(StudentDto studentDto) throws Exception {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws Exception {
        return false;
    }

    @Override
    public String getCount() throws Exception {
        long coursesCount = studentDAO.getStudentsCount();
        return String.valueOf(coursesCount);
    }
}
