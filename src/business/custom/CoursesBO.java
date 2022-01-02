package business.custom;

import business.SuperBO;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;

import java.util.ArrayList;

public interface CoursesBO extends SuperBO {
    CourseDto getCourse(String CourseId) throws Exception;

    ArrayList<CourseDto> getAllCourses() throws Exception;

    boolean addNewCourse(CourseDto courseDto) throws Exception;

    boolean updateCourse(CourseDto courseDto) throws Exception;

    String generateNewCourseId() throws Exception;

    String getCount() throws Exception;


}
