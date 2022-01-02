package business.custom.impl;

import business.custom.NewRegisterBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dao.custom.RegistrationDAO;
import dao.custom.StudentDAO;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;
import entity.Address;
import entity.Course;
import entity.Registration;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class NewRegisterBOImpl implements NewRegisterBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public StudentDto getStudent(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setContactNumber(student.getContactNumber());
        studentDto.setDob(student.getDob());
        studentDto.setGender(student.getGender());
        studentDto.setNoAndLane(student.getAddress().getNoAndLane());
        studentDto.setCity(student.getAddress().getCity());
        studentDto.setDistrict(student.getAddress().getDistrict());
        studentDto.setProvince(student.getAddress().getProvince());
        studentDto.setPostalCode(student.getAddress().getPostalCode());
        return studentDto;
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
    public ArrayList<RegistrationDto> getRegisteredCourses(String studentId) throws Exception {
        List<Registration> recordsByStudent = registrationDAO.getRecordsByStudent(studentId);
        ArrayList<RegistrationDto> dtoArrayList=new ArrayList<>();
        for (Registration registration : recordsByStudent) {
            dtoArrayList.add(new RegistrationDto(registration.getRegNo(),
                    registration.getStudent().getId(),
                    registration.getCourse().getCourseId(),
                    registration.getCourse().getCourseName(),
                    registration.getRegDate()));
        }
        return dtoArrayList;
    }

    @Override
    public boolean addNewStudent(StudentDto studentDto) throws Exception {
        Address address = new Address();
        address.setNoAndLane(studentDto.getNoAndLane());
        address.setCity(studentDto.getCity());
        address.setDistrict(studentDto.getDistrict());
        address.setProvince(studentDto.getProvince());
        address.setPostalCode(studentDto.getPostalCode());
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setContactNumber(studentDto.getContactNumber());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        student.setAddress(address);
        return studentDAO.add(student);
    }

    @Override
    public boolean addNewRegistration(RegistrationDto registrationDto) throws Exception {
        Registration registration = new Registration();
        registration.setRegNo(registrationDto.getRegNo());
        registration.setRegDate(registrationDto.getRegDate());
        registration.setStudent(studentDAO.find(registrationDto.getsId()));
        registration.setCourse(courseDAO.find(registrationDto.getcId()));
        return registrationDAO.add(registration);
    }

    @Override
    public boolean ifStudentExists(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        return student != null;
    }

    @Override
    public CourseDto getCourseByName(String courseName) throws Exception {
        Course course = courseDAO.getCourseByName(courseName);
        if (course!=null){
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseFee(course.getCourseFee());
            return courseDto;
        }else {
            return null;
        }
    }
}
