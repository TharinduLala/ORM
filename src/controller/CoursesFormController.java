package controller;

import business.BOFactory;
import business.custom.CoursesBO;
import com.jfoenix.controls.JFXButton;
import dto.CourseDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.AlertBox;
import view.Tm.CourseTm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CoursesFormController {
    public AnchorPane courseFormAP;
    public TableView<CourseTm> tblCourses;
    public TableColumn colCourseName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableColumn colEditDetails;
    public TableColumn colCourseId;
    public TextField txtCourseID;
    public TextField txtDuration;
    public TextField txtFee;
    public TextField txtCourseName;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXButton btnConfirmEdits;
    public TextField txtSearch;

    CoursesBO coursesBO = (CoursesBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.COURSE);
    ObservableList<CourseTm> courseTmObservableList = FXCollections.observableArrayList();

    public void initialize() {
        updateTable();
        searchInTable();
        txtCourseID.setDisable(true);
        btnSave.setDisable(true);
        btnConfirmEdits.setDisable(true);
    }

    @FXML
    public void btnAddNewAction() {
        try {
            /*  txtCourseID.setText(coursesBO.generateNewCourseId());*/
            txtCourseID.setDisable(false);
            btnSave.setDisable(false);
            btnConfirmEdits.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnSaveAction(){
        try {
            if (coursesBO.getCourse(txtCourseID.getText()) == null) {
                CourseDto courseDto = new CourseDto();
                courseDto.setCourseId(txtCourseID.getText());
                courseDto.setCourseName(txtCourseName.getText());
                courseDto.setCourseFee(new BigDecimal(txtFee.getText()));
                courseDto.setCourseDuration(txtDuration.getText());
                if (coursesBO.addNewCourse(courseDto)) {
                    new Alert(Alert.AlertType.INFORMATION, "Course added....").showAndWait();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try Again....").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Course already added....").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void btnCancelAction() {
        txtFee.clear();
        txtDuration.clear();
        txtCourseName.clear();
        txtCourseID.clear();
        txtCourseID.setDisable(true);
        btnSave.setDisable(true);
        btnConfirmEdits.setDisable(true);
    }

    @FXML
    public void btnConfirmEditsAction() {
        if (AlertBox.getAlert("You want to update this")) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(txtCourseID.getText());
            courseDto.setCourseName(txtCourseName.getText());
            courseDto.setCourseFee(new BigDecimal(txtFee.getText()));
            courseDto.setCourseDuration(txtDuration.getText());
            try {
                if (coursesBO.updateCourse(courseDto)) {
                    new Alert(Alert.AlertType.INFORMATION, "Course updated....").showAndWait();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try Again....").showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void searchInTable() {
        try {
            FilteredList<CourseTm> filteredData = new FilteredList<>(courseTmObservableList, b -> true);
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(CourseTm -> {
                if (newValue.isEmpty()) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();
                if (CourseTm.getCourseId().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (CourseTm.getCourseName().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else return CourseTm.getDuration().toLowerCase().contains(searchKeyWord);
            }));
            SortedList<CourseTm> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblCourses.comparatorProperty());
            tblCourses.setItems(sortedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllCourses(ObservableList<CourseTm> courseTms) {
        tblCourses.setItems(courseTms);
    }

    private void updateTable() {
        try {
            ArrayList<CourseDto> allCourses = coursesBO.getAllCourses();
            for (CourseDto courseDto : allCourses) {
                Button btn = new Button("Edit");
                courseTmObservableList.add(new CourseTm(
                        courseDto.getCourseId(),
                        courseDto.getCourseName(),
                        courseDto.getCourseDuration(),
                        courseDto.getCourseFee(),
                        btn));
                btn.setOnAction((e) -> {
                    btnSave.setDisable(true);
                    txtCourseID.setDisable(true);
                    btnConfirmEdits.setDisable(false);
                    txtCourseID.setText(courseDto.getCourseId());
                    txtCourseName.setText(courseDto.getCourseName());
                    txtDuration.setText(courseDto.getCourseDuration());
                    txtFee.setText(courseDto.getCourseFee().toString());
                });
            }
            tblCourses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseId"));
            tblCourses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
            tblCourses.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
            tblCourses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));
            tblCourses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("edit"));
            loadAllCourses(courseTmObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshPage() {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("../view/CoursesForm.fxml"));
            courseFormAP.getChildren().clear();
            courseFormAP.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
