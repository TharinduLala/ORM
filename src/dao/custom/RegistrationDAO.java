package dao.custom;

import dao.SuperDAO;
import entity.Registration;
import entity.Student;

import java.util.List;

public interface RegistrationDAO extends SuperDAO<Registration,String> {

    List<Registration> getRecordsByStudent(Student student) throws Exception;
}
