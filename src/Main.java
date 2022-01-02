import entity.Address;
import entity.Course;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        /*Address address = new Address(
                "50", "Horana", "kalutara", "western", 12500
        );
        Student student = new Student();
        student.setId("001");
        student.setName("kamal");
        student.setAddress(address);
        student.setContactNumber(715455515);
        student.setDob(LocalDate.of(2011, 2, 24));
        student.setGender("male");



        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        Configuration configuration = new Configuration().mergeProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Registration.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        *//*Registration registration = new Registration();
        registration.setRegDate(LocalDate.now());
        registration.setCourse("c001");
        registration.setRegNo("r001");*//*
        Student student1 = session.get(Student.class, "001");
       *//* registration.setStudent(student1);
        student1.getRegistrations().add(registration);
        session.save(registration);
        session.update(student1);*//*

        transaction.commit();
        session.close();
        for (Registration r:student1.getRegistrations()
             ) {
            System.out.println(r.toString() );
        }*/

       /* Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Course where courseName=:n");
        query.setParameter("n","Electronics");
        List<Course> list = query.list();
        for (Course c : list) {
            System.out.println(c.getCourseId());
            System.out.println(c.getCourseName());
        }
        transaction.commit();
        session.close();*/
    }
}
