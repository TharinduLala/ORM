package controller;

import business.BOFactory;
import business.custom.RegistrationsBO;
import business.custom.StudentsBO;
import com.jfoenix.controls.JFXButton;
import dto.RegistrationDto;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.AlertBox;
import view.Tm.RegisteredCourseTm;
import view.Tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentsFormController implements Initializable {
    public AnchorPane StudentsFormAP;
    public TextField txtSearch;
    public TextField txtName;
    public TextField txtContactNo;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtPostalCode;
    public ComboBox<String> cmbDistricts;
    public ComboBox<String> cmbProvincesList;
    public TableView<RegisteredCourseTm> tblRegDetails;
    public TextField txtNic;
    public DatePicker txtBirthday;
    public TableView<StudentTm> tblStudents;
    public JFXButton btnConfirmEdits;
    public JFXButton btnEditDetails;
    public RadioButton radioBtnFemale;
    public RadioButton radioBtnMale;
    public JFXButton btnCancel;
    ToggleGroup group = new ToggleGroup();

    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    ObservableList<StudentTm> students = FXCollections.observableArrayList();
    RegistrationsBO registerBO = (RegistrationsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.REGISTRATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchInTable();
        loadDistrictAndProvinceCombo();
        disableEdit(false);
        btnConfirmEdits.setDisable(true);
        btnEditDetails.setDisable(true);
        radioBtnFemale.setToggleGroup(group);
        radioBtnFemale.setUserData("Female");
        radioBtnMale.setToggleGroup(group);
        radioBtnMale.setUserData("Male");
    }


    @FXML
    public void btnEditDetailsAction() {
        disableEdit(true);
        btnConfirmEdits.setDisable(false);
        btnEditDetails.setDisable(true);
    }

    @FXML
    public void btnConfirmEditsAction() {
        if (AlertBox.getAlert("You want to save edits?")) {
            StudentDto studentDto = new StudentDto();
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
            try {
                studentsBO.updateStudent(studentDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshPage();
        }
    }

    @FXML
    public void btnCancelAction() {
        refreshPage();
    }

    private void updateTable() {
        try {
            ArrayList<StudentDto> allStudents = studentsBO.getAllStudents();
            for (StudentDto studentDto : allStudents) {
                Button btn = new Button("view");
                students.add(new StudentTm(studentDto.getId(), studentDto.getName(),
                        btn));
                btn.setOnAction((e) -> {
                    viewDetails(studentDto);
                    btnEditDetails.setDisable(false);
                });
            }
            tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("details"));
            tblStudents.setItems(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchInTable() {
        try {
            FilteredList<StudentTm> filteredData = new FilteredList<>(students, b -> true);
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(StudentTm -> {
                if (newValue.isEmpty()) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();
                if (StudentTm.getId().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else return StudentTm.getName().toLowerCase().contains(searchKeyWord);
            }));
            SortedList<StudentTm> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblStudents.comparatorProperty());
            tblStudents.setItems(sortedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDistrictAndProvinceCombo() {
        ObservableList<String> districts = FXCollections.observableArrayList(
                "Ampara", "Anuradhapura", "Badulla", "Batticaloa", "Colombo",
                "Galle", "Gampaha", "Hambantota", "Jaffna", "Kalutara", "Kandy", "Kegalle",
                "Kilinochchi", "Kurunegala", "Mannar", "Matale", "Matara", "Monaragala", "Mullaitivu",
                "Nuwara Eliya", "Polonnaruwa", "Puttalam", "Ratnapura", "Trincomalee", "Vavuniya"
        );
        cmbDistricts.setItems(districts);
        ObservableList<String> provinces = FXCollections.observableArrayList(
                "Central", "Eastern", "North Central", "Northern",
                "North Western", "Sabaragamuwa", "Southern", "Uva", "Western"
        );
        cmbProvincesList.setItems(provinces);
    }

    private void viewDetails(StudentDto studentDto) {
        try {
            txtNic.setText(studentDto.getId());
            txtName.setText(studentDto.getName());
            txtBirthday.setValue(studentDto.getDob());
            String gender = studentDto.getGender();
            if (gender.equals("Female")) {
                radioBtnFemale.fire();
            } else if (gender.equals("Male")) {
                radioBtnMale.fire();
            }
            txtCity.setText(studentDto.getCity());
            txtAddress.setText(studentDto.getNoAndLane());
            txtPostalCode.setText(String.valueOf(studentDto.getPostalCode()));
            cmbDistricts.setValue(studentDto.getDistrict());
            cmbProvincesList.setValue(studentDto.getProvince());
            txtContactNo.setText(String.valueOf(studentDto.getContactNumber()));

            ArrayList<RegistrationDto> registeredCourses = registerBO.getRegisteredCourses(studentDto.getId());
            ObservableList<RegisteredCourseTm> registeredCourseTms = FXCollections.observableArrayList();
            for (RegistrationDto registeredCourse : registeredCourses) {
                RegisteredCourseTm registeredCourseTm = new RegisteredCourseTm();
                registeredCourseTm.setCourseId(registeredCourse.getcId());
                registeredCourseTm.setCourseName(registeredCourse.getcName());
                registeredCourseTm.setRegDate(registeredCourse.getRegDate());
                registeredCourseTm.setRegNo(registeredCourse.getRegNo());
                registeredCourseTms.add(registeredCourseTm);
            }
            tblRegDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
            tblRegDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseId"));
            tblRegDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseName"));
            tblRegDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("regDate"));
            tblRegDetails.setItems(registeredCourseTms);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableEdit(boolean b) {
        txtAddress.setEditable(b);
        txtBirthday.setEditable(b);
        txtPostalCode.setEditable(b);
        txtContactNo.setEditable(b);
        txtCity.setEditable(b);
        txtName.setEditable(b);
        txtBirthday.setEditable(b);
    }

    private void refreshPage() {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("../view/StudentsForm.fxml"));
            StudentsFormAP.getChildren().clear();
            StudentsFormAP.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
