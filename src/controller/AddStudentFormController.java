package controller;

import business.BOFactory;
import business.custom.CoursesBO;
import business.custom.RegistrationsBO;
import business.custom.StudentsBO;
import com.jfoenix.controls.JFXButton;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Tm.RegisteredCourseTm;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class
AddStudentFormController implements Initializable {
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
    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.COURSE);
    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    RegistrationsBO registrationsBO = (RegistrationsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.REGISTRATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblRegDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
        tblRegDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tblRegDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblRegDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("regDate"));

        loadDistrictAndProvinceCombo();
        loadAllCourses();
        disableCourseFields(false);
        disableStudentFields(false);
        btnAddCourse.setDisable(true);
        txtSearch.setDisable(true);
        btnSearch.setDisable(true);

        radioBtnFemale.setToggleGroup(group);
        radioBtnFemale.setUserData("Female");
        radioBtnMale.setToggleGroup(group);
        radioBtnMale.setUserData("Male");
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {

            }
        });


        cmbCourseName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    CourseDto course = coursesBO.getCourseByName(newValue);
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

    @FXML
    public void btnSearchAction() {
        try {
            boolean exists = studentsBO.ifStudentExists(txtSearch.getText());
            if (exists) {
                StudentDto student = studentsBO.getStudent(txtSearch.getText());
                disableStudentFields(false);
                txtNic.setText(student.getId());
                txtName.setText(student.getName());
                txtBirthday.setValue(student.getDob());
                String gender = student.getGender();
                if (gender.equals("Female")) {
                    radioBtnFemale.fire();
                } else if (gender.equals("Male")) {
                    radioBtnMale.fire();
                }
                txtCity.setText(student.getCity());
                txtAddress.setText(student.getNoAndLane());
                txtPostalCode.setText(String.valueOf(student.getPostalCode()));
                cmbDistricts.setValue(student.getDistrict());
                cmbProvincesList.setValue(student.getProvince());
                txtContactNo.setText(String.valueOf(student.getContactNumber()));
                loadRegisteredDetails(txtNic.getText());
                disableCourseFields(false);
            } else {
                new Alert(Alert.AlertType.WARNING, "Student not exists").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnYesAction() {
        btnYes.setDisable(true);
        btnNo.setDisable(false);
        disableStudentFields(false);
        txtSearch.setDisable(false);
        btnSearch.setDisable(false);
        disableCourseFields(true);
        new Alert(Alert.AlertType.INFORMATION, "please search student by student NIC").showAndWait();
    }

    @FXML
    public void btnNoAction() {
        txtSearch.setDisable(true);
        btnSearch.setDisable(true);
        disableCourseFields(false);
        disableStudentFields(true);
        radioBtnMale.setDisable(false);
        radioBtnFemale.setDisable(false);
        btnYes.setDisable(false);
        btnNo.setDisable(true);
        new Alert(Alert.AlertType.INFORMATION, "please fill below details and select course").showAndWait();
    }

    @FXML
    public void btnAddCourseAction() {
        if (txtNic.getText().equals("")) {
            new Alert(Alert.AlertType.INFORMATION, "Please select or add student details ").showAndWait();
        } else {
            boolean exists = false;
            for (RegisteredCourseTm registeredCourseTm : registeredCourseTms) {
                if (registeredCourseTm.getCourseId().equals(txtCourseId.getText())) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                new Alert(Alert.AlertType.WARNING, "Student already register for this course ").showAndWait();
            } else {
                RegisteredCourseTm registeredCourseTm = new RegisteredCourseTm();
                registeredCourseTm.setCourseId(txtCourseId.getText());
                registeredCourseTm.setCourseName(cmbCourseName.getValue());
                registeredCourseTm.setRegDate(LocalDate.now());
                registeredCourseTm.setRegNo(txtCourseId.getText() + "/" + txtNic.getText());
                registeredCourseTms.add(registeredCourseTm);
                tblRegDetails.setItems(registeredCourseTms);
                btnAddCourse.setDisable(true);
            }
        }
    }

    @FXML
    public void btnSaveAction() {
        try {
            StudentDto studentDto = new StudentDto();
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setRegNo(txtCourseId.getText() + "/" + txtNic.getText());
            registrationDto.setcId(txtCourseId.getText());
            registrationDto.setsId(txtNic.getText());
            registrationDto.setcName(cmbCourseName.getValue());
            registrationDto.setRegDate(LocalDate.now());
            studentDto.setId(txtNic.getText());
            studentDto.setName(txtName.getText());
            studentDto.setDob(txtBirthday.getValue());
            studentDto.setGender(group.getSelectedToggle().getUserData().toString());
            studentDto.setCity(txtCity.getText());
            studentDto.setNoAndLane(txtAddress.getText());
            studentDto.setPostalCode(Integer.parseInt(txtPostalCode.getText()));
            studentDto.setDistrict(cmbDistricts.getValue());
            studentDto.setProvince(cmbProvincesList.getValue());
            studentDto.setContactNumber(Integer.parseInt(txtContactNo.getText()));

            boolean exists = studentsBO.ifStudentExists(txtSearch.getText());
            if (exists) {
                registrationsBO.addNewRegistration(registrationDto);
            } else {
                studentsBO.addNewStudent(studentDto);
                registrationsBO.addNewRegistration(registrationDto);
            }
            new Alert(Alert.AlertType.INFORMATION, "Registration done....").showAndWait();
            btnCancelAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnCancelAction() {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("../view/AddStudentForm.fxml"));
            addStudentFormAP.getChildren().clear();
            addStudentFormAP.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disableStudentFields(boolean b) {
        txtNic.setEditable(b);
        txtName.setEditable(b);
        txtBirthday.setEditable(b);
        radioBtnMale.setDisable(b);
        radioBtnFemale.setDisable(b);
        txtAddress.setEditable(b);
        txtCity.setEditable(b);
        txtPostalCode.setEditable(b);
        cmbProvincesList.setEditable(b);
        cmbDistricts.setEditable(b);
        txtContactNo.setEditable(b);
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
            ArrayList<CourseDto> allCourses = coursesBO.getAllCourses();
            ObservableList<String> courseNames = FXCollections.observableArrayList();
            for (CourseDto courseDto : allCourses) {
                courseNames.add(courseDto.getCourseName());
            }
            cmbCourseName.getItems().addAll(courseNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRegisteredDetails(String studentId) {
        try {
            ArrayList<RegistrationDto> registeredCourses = registrationsBO.getRegisteredCourses(studentId);
            for (RegistrationDto registeredCourse : registeredCourses) {
                RegisteredCourseTm registeredCourseTm = new RegisteredCourseTm();
                registeredCourseTm.setCourseId(registeredCourse.getcId());
                registeredCourseTm.setCourseName(registeredCourse.getcName());
                registeredCourseTm.setRegDate(registeredCourse.getRegDate());
                registeredCourseTm.setRegNo(registeredCourse.getRegNo());
                registeredCourseTms.add(registeredCourseTm);
            }
            tblRegDetails.setItems(registeredCourseTms);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}