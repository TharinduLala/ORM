package business.custom.impl;

import business.custom.CoursesBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dto.CourseDto;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesBOImpl implements CoursesBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public CourseDto getCourse(String courseId) throws Exception {
        Course course = courseDAO.find(courseId);
        if (course != null) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseFee(course.getCourseFee());
            return courseDto;
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<CourseDto> getAllCourses() throws Exception {
        List<Course> courses = courseDAO.findAll();
        ArrayList<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses
        ) {
            courseDtos.add(new CourseDto(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseDuration(),
                    course.getCourseFee()));
        }
        return courseDtos;
    }

    @Override
    public boolean addNewCourse(CourseDto courseDto) throws Exception {
        Course course = new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setCourseFee(courseDto.getCourseFee());
        return courseDAO.add(course);
    }

    @Override
    public boolean updateCourse(CourseDto courseDto) throws Exception {
        Course course = new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setCourseFee(courseDto.getCourseFee());
        return courseDAO.update(course);
    }

    @Override
    public String generateNewCourseId() throws Exception {
        Course lastRecord = courseDAO.getLastRecord();
        if (lastRecord == null) {
            return "C001";
        } else {
            int newCourseId = Integer.parseInt(lastRecord.getCourseId().replace("C", "")) + 1;
            return String.format("C%03d", newCourseId);
        }
    }

    @Override
    public String getCount() throws Exception {
        long coursesCount = courseDAO.getCoursesCount();
        return String.valueOf(coursesCount);
    }

    @Override
    public CourseDto getCourseByName(String courseName) throws Exception {
        Course course = courseDAO.getCourseByName(courseName);
        if (course != null) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseFee(course.getCourseFee());
            return courseDto;
        } else {
            return null;
        }
    }


}
