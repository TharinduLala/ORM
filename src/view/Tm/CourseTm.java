package view.Tm;

import javafx.scene.control.*;
import java.math.BigDecimal;

public class CourseTm {
    private String courseId;
    private String courseName;
    private String duration;
    private BigDecimal fee;
    private Button edit;

    public CourseTm() {
    }

    public CourseTm(String courseId, String courseName, String duration, BigDecimal fee, Button edit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
        this.edit = edit;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return "CourseTm{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", edit=" + edit +
                '}';
    }
}
