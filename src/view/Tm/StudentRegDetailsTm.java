package view.Tm;

import java.awt.*;
import java.time.LocalDate;

public class StudentRegDetailsTm {
    private String regNo;
    private String courseId;
    private LocalDate regDate;
    private Button removeCourse;

    public StudentRegDetailsTm() {
    }

    public StudentRegDetailsTm(String regNo, String courseId, LocalDate regDate, Button removeCourse) {
        this.regNo = regNo;
        this.courseId = courseId;
        this.regDate = regDate;
        this.removeCourse = removeCourse;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Button getRemoveCourse() {
        return removeCourse;
    }

    public void setRemoveCourse(Button removeCourse) {
        this.removeCourse = removeCourse;
    }

    @Override
    public String toString() {
        return "StudentRegDetailsTm{" +
                "regNo='" + regNo + '\'' +
                ", courseId='" + courseId + '\'' +
                ", regDate=" + regDate +
                ", removeCourse=" + removeCourse +
                '}';
    }
}
