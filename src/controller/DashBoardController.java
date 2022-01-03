package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    public AnchorPane dashBoardAP;
    public AnchorPane windowsLoadAp;
    public JFXButton btnDashBord;
    public JFXButton btnRegStudent;
    public JFXButton btnStudents;
    public JFXButton btnRegistrations;
    public JFXButton btnCourses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUi("DashBoardView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUi(String name) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/" + name + ".fxml"));
        windowsLoadAp.getChildren().clear();
        windowsLoadAp.getChildren().add(load);
    }

    @FXML
    public void openDashBoard() {
        try {
            loadUi("DashBoardView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openRegisterStudent() {
        try {
            loadUi("AddStudentForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openStudentsPage() {
        try {
            loadUi("StudentsForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openRegistrations() {
        try {
            loadUi("RegistrationsForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCoursesPage() {
        try {
            loadUi("CoursesForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
