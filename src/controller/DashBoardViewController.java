package controller;

import business.BOFactory;
import business.custom.CoursesBO;
import business.custom.StudentsBO;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardViewController implements Initializable {
    public AnchorPane dashBoardViewAp;
    public Label lblDate;
    public Label lblTime;
    public Label lblDay;
    public Label lblStudentCount;
    public Label lblCoursesCount;
    public JFXButton btnStudentMoreInfo;
    public JFXButton btnCourseMoreInfo;

    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.COURSE);
    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();
        try {
            lblCoursesCount.setText(coursesBO.getCount());
            lblStudentCount.setText(studentsBO.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openStudentPge() {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("../view/StudentsForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashBoardViewAp.getChildren().clear();
        dashBoardViewAp.getChildren().add(load);
    }

    @FXML
    public void openCoursesPage() {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("../view/CoursesForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashBoardViewAp.getChildren().clear();
        dashBoardViewAp.getChildren().add(load);
    }

    private void setDateAndTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        lblDay.setText(dayFormat.format(date));
        lblDate.setText(simpleDateFormat.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
            lblTime.setText(format.format(new Date()));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
}
