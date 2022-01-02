package business.custom;

import business.SuperBO;
import dto.StudentDto;

import java.util.ArrayList;

public interface StudentsBO extends SuperBO {
    StudentDto getStudent(String studentId) throws Exception;

    ArrayList<StudentDto> getAllStudents() throws Exception;

    boolean addNewStudent(StudentDto studentDto) throws Exception;

    boolean updateStudent(StudentDto studentDto) throws Exception;

    String getCount() throws Exception;
}
