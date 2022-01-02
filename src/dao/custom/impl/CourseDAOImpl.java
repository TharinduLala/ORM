package dao.custom.impl;

import dao.custom.CourseDAO;
import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, s);
        session.delete(course);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course find(String s) throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, s);
        transaction.commit();
        session.close();
        return course;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<Course> list = null;
        Query courses = session.createQuery("from Course");
        list = courses.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Course getLastRecord() throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query lastRecord = session.createQuery("FROM Course ORDER BY courseId DESC");
        lastRecord.setMaxResults(1);
        Course last = (Course) lastRecord.uniqueResult();
        transaction.commit();
        session.close();
        return last;
    }

    @Override
    public long getCoursesCount() throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Course");
        Iterator iterate = query.iterate();
        long count = (long) iterate.next();
        transaction.commit();
        session.close();
        return count;
    }

    @Override
    public Course getCourseByName(String name) throws Exception {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Course where courseName=:n");
        query.setParameter("n",name);
        query.setMaxResults(1);
        Course course = (Course) query.uniqueResult();
        transaction.commit();
        session.close();
        return course;
    }
}
