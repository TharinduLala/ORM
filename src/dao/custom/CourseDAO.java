package dao.custom;

import dao.SuperDAO;
import entity.Course;


public interface CourseDAO extends SuperDAO<Course,String> {
    Course getLastRecord() throws Exception;

    long getCoursesCount() throws Exception;

    Course getCourseByName(String name) throws Exception;
}
