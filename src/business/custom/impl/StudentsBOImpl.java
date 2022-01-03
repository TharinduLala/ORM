package business.custom.impl;

import business.custom.StudentsBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDto;
import entity.Address;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsBOImpl implements StudentsBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

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
    public ArrayList<StudentDto> getAllStudents() throws Exception {
        List<Student> students = studentDAO.findAll();
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students
        ) {
            studentDtos.add(new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getContactNumber(),
                    student.getDob(),
                    student.getGender(),
                    student.getAddress().getNoAndLane(),
                    student.getAddress().getCity(),
                    student.getAddress().getDistrict(),
                    student.getAddress().getProvince(),
                    student.getAddress().getPostalCode()
            ));
        }
        return studentDtos;
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
    public boolean updateStudent(StudentDto studentDto) throws Exception {
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
        return studentDAO.update(student);
    }

    @Override
    public String getCount() throws Exception {
        long coursesCount = studentDAO.getStudentsCount();
        return String.valueOf(coursesCount);
    }

    @Override
    public boolean ifStudentExists(String studentId) throws Exception {
        Student student = studentDAO.find(studentId);
        return student != null;
    }
}
