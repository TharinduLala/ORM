package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StudentsFormController {
    public AnchorPane StudentsFormAP;
    public TextField txtSearch;
    public TextField txtName;
    public TextField txtContactNo;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtPostalCode;
    public ComboBox<String> cmbDistricts;
    public ComboBox<String> cmbProvincesList;
    public TableView tblRegDetails;
    public TableColumn colRegNo;
    public TableColumn colCourseId;
    public TableColumn colRegDate;
    public TableColumn colRemove;
    public TextField txtNic;
    public CheckBox checkBoxMale;
    public CheckBox checkBoxFemale;
    public DatePicker txtBirthday;
    public TableView tblStudents;
    public TableColumn colNic;
    public TableColumn colName;
    public TableColumn colDetails;
    public JFXButton btnConfirmEdits;
    public JFXButton btnEditDetails;

    public void initialize(){
        loadDistrictAndProvinceCombo();
    }


    public void btnEditDetailsAction(MouseEvent mouseEvent) {
    }

    public void btnConfirmEditsAction(MouseEvent mouseEvent) {
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

}
