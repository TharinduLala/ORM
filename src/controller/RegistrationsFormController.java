package controller;

import business.BOFactory;
import business.custom.RegistrationsBO;
import dto.RegistrationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.Tm.RegistrationsTm;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationsFormController implements Initializable {
    public AnchorPane registrationsFormAP;
    public TableView<RegistrationsTm> tblRegDetails;
    public TextField txtSearch;

    RegistrationsBO registrationsBO = (RegistrationsBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.REGISTRATION);
    ObservableList<RegistrationsTm> registrationsTms = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchInTable();
    }

    private void searchInTable() {
        try {
            FilteredList<RegistrationsTm> filteredData = new FilteredList<>(registrationsTms, b -> true);
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(RegistrationsTm -> {
                if (newValue.isEmpty()) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();
                if (RegistrationsTm.getRegNo().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (RegistrationsTm.getCourseId().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (RegistrationsTm.getCourseName().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else return RegistrationsTm.getStudentId().toLowerCase().contains(searchKeyWord);
            }));
            SortedList<RegistrationsTm> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblRegDetails.comparatorProperty());
            tblRegDetails.setItems(sortedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        try {
            ArrayList<RegistrationDto> allRegistrations = registrationsBO.getAllRegistrations();

            for (RegistrationDto registrationDto : allRegistrations) {
                registrationsTms.add(new RegistrationsTm(
                        registrationDto.getRegNo(),
                        registrationDto.getsId(),
                        registrationDto.getcId(),
                        registrationDto.getcName(),
                        registrationDto.getRegDate()
                ));
            }
            tblRegDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
            tblRegDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
            tblRegDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseId"));
            tblRegDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("courseName"));
            tblRegDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("regDate"));

            tblRegDetails.setItems(registrationsTms);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
