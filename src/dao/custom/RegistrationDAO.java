package dao.custom;

import dao.SuperDAO;
import entity.Registration;

import java.util.List;

public interface RegistrationDAO extends SuperDAO<Registration,String> {
    List<Registration> getRecordsByStudent(String studentId) throws Exception;
}
