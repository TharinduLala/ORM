package view.Tm;

import java.time.LocalDate;

public class RegistrationsTm {
    private String regNo;
    private String studentId;
    private String courseId;
    private String courseName;
    private LocalDate regDate;

    public RegistrationsTm() {
    }

    public RegistrationsTm(String regNo, String studentId, String courseId, String courseName, LocalDate regDate) {
        this.regNo = regNo;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.regDate = regDate;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "RegistrationsTm{" +
                "regNo='" + regNo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
