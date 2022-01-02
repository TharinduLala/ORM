package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardController {
    public AnchorPane dashBoardAP;
    public AnchorPane windowsLoadAp;
    public JFXButton btnDashBord;
    public JFXButton btnRegStudent;
    public JFXButton btnStudents;
    public JFXButton btnRegistrations;
    public JFXButton btnCourses;

    public void initialize() {
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

    public void openDashBoard(MouseEvent mouseEvent) {
        try {
            loadUi("DashBoardView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRegisterStudent(MouseEvent mouseEvent) {
        try {
            loadUi("AddStudentForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openStudentsPage(MouseEvent mouseEvent) {
        try {
            loadUi("StudentsForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRegistrations(MouseEvent mouseEvent) {
        try {
            loadUi("RegistrationsForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCoursesPage(MouseEvent mouseEvent) {
        try {
            loadUi("CoursesForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
