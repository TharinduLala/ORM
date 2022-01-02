package business.custom;

import business.SuperBO;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;

import java.util.ArrayList;

public interface NewRegisterBO extends SuperBO {
    StudentDto getStudent(String studentId) throws Exception;

    ArrayList<CourseDto> getAllCourses() throws Exception;

    ArrayList<RegistrationDto> getRegisteredCourses(String studentId) throws Exception;

    boolean addNewStudent(StudentDto studentDto) throws Exception;

    boolean addNewRegistration(RegistrationDto registrationDto) throws Exception;

    boolean ifStudentExists(String studentId) throws Exception;

    CourseDto getCourseByName(String courseName) throws Exception;
}
