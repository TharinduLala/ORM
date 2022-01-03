package business.custom;

import business.SuperBO;
import dto.RegistrationDto;

import java.util.ArrayList;

public interface RegistrationsBO extends SuperBO {

    ArrayList<RegistrationDto> getAllRegistrations() throws Exception;

    ArrayList<RegistrationDto> getRegisteredCourses(String studentId) throws Exception;

    boolean addNewRegistration(RegistrationDto registrationDto) throws Exception;

}
