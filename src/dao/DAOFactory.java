package dao;


import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.RegistrationDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes) {
            case STUDENT:return new StudentDAOImpl();
            case COURSE:return new CourseDAOImpl();
            case REGISTRATION:return new RegistrationDAOImpl();
            default:return null;
        }
    }

    public enum DAOTypes{
        STUDENT,COURSE,REGISTRATION
    }
}
