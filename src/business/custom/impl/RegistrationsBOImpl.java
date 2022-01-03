package business.custom.impl;

import business.custom.RegistrationsBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dao.custom.RegistrationDAO;
import dao.custom.StudentDAO;
import dto.RegistrationDto;
import entity.Registration;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationsBOImpl implements RegistrationsBO {
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public ArrayList<RegistrationDto> getAllRegistrations() throws Exception {
        List<Registration> registrationList = registrationDAO.findAll();

        ArrayList<RegistrationDto> registrationDtos = new ArrayList<>();

        for (Registration registration : registrationList) {
            registrationDtos.add(new RegistrationDto(
                    registration.getRegNo(),
                    registration.getStudent().getId(),
                    registration.getCourse().getCourseId(),
                    registration.getCourse().getCourseName(),
                    registration.getRegDate())
            );
        }
        return registrationDtos;
    }

    @Override
    public ArrayList<RegistrationDto> getRegisteredCourses(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        List<Registration> recordsByStudent = registrationDAO.getRecordsByStudent(student);
        ArrayList<RegistrationDto> dtoArrayList = new ArrayList<>();
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
    public boolean addNewRegistration(RegistrationDto registrationDto) throws Exception {
        Student student = studentDAO.find(registrationDto.getsId());
        Registration registration = new Registration();
        registration.setRegNo(registrationDto.getRegNo());
        registration.setRegDate(registrationDto.getRegDate());
        registration.setStudent(student);
        registration.setCourse(courseDAO.find(registrationDto.getcId()));
        student.getRegistrations().add(registration);
        studentDAO.update(student);
        return registrationDAO.add(registration);
    }
}
