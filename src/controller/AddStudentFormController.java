package controller;

import business.BOFactory;
import business.custom.CoursesBO;
import business.custom.NewRegisterBO;
import business.custom.StudentsBO;
import com.jfoenix.controls.JFXButton;
import dto.CourseDto;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.Tm.RegisteredCourseTm;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class
AddStudentFormController {
    public ComboBox<String> cmbDistricts;
    public ComboBox<String> cmbProvincesList;
    public AnchorPane addStudentFormAP;
    public TextField txtSearch;
    public TextField txtName;
    public TextField txtContactNo;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtPostalCode;
    public RadioButton radioBtnFemale;
    public RadioButton radioBtnMale;
    public DatePicker txtBirthday;
    public TableView<RegisteredCourseTm> tblRegDetails;
    public TableColumn colRegNo;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colRegDate;
    public TextField txtNic;
    public JFXButton btnSearch;
    public JFXButton btnYes;
    public JFXButton btnNo;
    public ComboBox<String> cmbCourseName;
    public TextField txtCourseId;
    public TextField txtDuration;
    public TextField txtFee;
    public JFXButton btnAddCourse;
    public JFXButton btnSave;
    public JFXButton btnCancel;

    ToggleGroup group = new ToggleGroup();
    ObservableList<RegisteredCourseTm> registeredCourseTms = FXCollections.observableArrayList();
    NewRegisterBO registerBO = (NewRegisterBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.NEW_REGISTRATION);

    public void initialize() {
        tblRegDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
        tblRegDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tblRegDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblRegDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("regDate"));

        loadDistrictAndProvinceCombo();
        loadAllCourses();
        disableCourseFields(true);
        disableStudentFields(true);
        btnAddCourse.setDisable(true);
        txtSearch.setDisable(true);
        btnSearch.setDisable(true);

        radioBtnFemale.setToggleGroup(group);
        radioBtnFemale.setUserData("Female");
        radioBtnMale.setToggleGroup(group);
        radioBtnMale.setUserData("Male");
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                System.out.println(group.getSelectedToggle().getUserData().toString());
            }
        });


        cmbCourseName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    CourseDto course = registerBO.getCourseByName(newValue);
                    if (course != null) {
                        txtCourseId.setText(course.getCourseId());
                        txtDuration.setText(course.getCourseDuration());
                        txtFee.setText(String.valueOf(course.getCourseFee()));
                        btnAddCourse.setDisable(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                txtFee.clear();
                txtDuration.clear();
                txtCourseId.clear();
            }
        });
    }

    public void btnSearchAction(MouseEvent mouseEvent) {
        try {
            StudentDto student = registerBO.getStudent(txtSearch.getText());
            if (student!=null){
                txtNic.setText(student.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        disableCourseFields(false);
    }

    public void btnYesAction(MouseEvent mouseEvent) {
        btnYes.setDisable(true);
        btnNo.setDisable(false);
        disableStudentFields(true);
        txtSearch.setDisable(false);
        btnSearch.setDisable(false);
        disableCourseFields(true);
    }

    public void btnNoAction(MouseEvent mouseEvent) {
        txtSearch.setDisable(true);
        btnSearch.setDisable(true);
        disableCourseFields(false);
        disableStudentFields(false);
        btnYes.setDisable(false);
        btnNo.setDisable(true);
    }

    public void btnAddCourseAction(MouseEvent mouseEvent) {
        if (txtNic.getText().equals("")){
            new Alert(Alert.AlertType.INFORMATION, "Please select or add student details ").showAndWait();
        }else {
            RegisteredCourseTm registeredCourseTm = new RegisteredCourseTm();
            registeredCourseTm.setCourseId(txtCourseId.getText());
            registeredCourseTm.setCourseName(cmbCourseName.getValue());
            registeredCourseTm.setRegDate(LocalDate.now());
            registeredCourseTm.setRegNo(txtCourseId.getText() + "/" + txtNic.getText());
            registeredCourseTms.add(registeredCourseTm);
            tblRegDetails.setItems(registeredCourseTms);
        }
    }

    public void btnSaveAction(MouseEvent mouseEvent) {
    }

    public void btnCancelAction(MouseEvent mouseEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("../view/AddStudentForm.fxml"));
            addStudentFormAP.getChildren().clear();
            addStudentFormAP.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disableStudentFields(boolean b) {
        txtNic.setDisable(b);
        txtName.setDisable(b);
        txtBirthday.setDisable(b);
        radioBtnMale.setDisable(b);
        radioBtnFemale.setDisable(b);
        txtAddress.setDisable(b);
        txtCity.setDisable(b);
        txtPostalCode.setDisable(b);
        cmbProvincesList.setDisable(b);
        cmbDistricts.setDisable(b);
        txtContactNo.setDisable(b);
    }

    private void disableCourseFields(boolean b) {
        cmbCourseName.setDisable(b);
        btnSave.setDisable(b);

    }

    private void loadDistrictAndProvinceCombo() {
        ObservableList<String> districtsList = FXCollections.observableArrayList(
                "Ampara", "Anuradhapura", "Badulla", "Batticaloa", "Colombo",
                "Galle", "Gampaha", "Hambantota", "Jaffna", "Kalutara", "Kandy", "Kegalle",
                "Kilinochchi", "Kurunegala", "Mannar", "Matale", "Matara", "Monaragala", "Mullaitivu",
                "Nuwara Eliya", "Polonnaruwa", "Puttalam", "Ratnapura", "Trincomalee", "Vavuniya"
        );
        cmbDistricts.setItems(districtsList);
        ObservableList<String> provincesList = FXCollections.observableArrayList(
                "Central", "Eastern", "North Central", "Northern",
                "North Western", "Sabaragamuwa", "Southern", "Uva", "Western"
        );
        cmbProvincesList.setItems(provincesList);
    }

    private void loadAllCourses() {

        try {
            ArrayList<CourseDto> allCourses = registerBO.getAllCourses();
            ObservableList<String> courseNames = FXCollections.observableArrayList();
            for (CourseDto courseDto : allCourses) {
                courseNames.add(courseDto.getCourseName());
            }
            cmbCourseName.getItems().addAll(courseNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}