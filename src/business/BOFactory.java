package business;

import business.custom.impl.CoursesBOImpl;
import business.custom.impl.NewRegisterBOImpl;
import business.custom.impl.RegistrationsBOImpl;
import business.custom.impl.StudentsBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public SuperBO getBo(BoTypes boTypes){
        switch (boTypes) {
            case NEW_REGISTRATION:return new NewRegisterBOImpl();
            case COURSE:return new CoursesBOImpl();
            case STUDENT:return new StudentsBOImpl();
            case REGISTRATION:return new RegistrationsBOImpl();
            default:return null;
        }
    }

    public enum BoTypes{
        REGISTRATION,COURSE,NEW_REGISTRATION,STUDENT
    }
}
