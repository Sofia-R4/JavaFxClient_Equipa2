package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFxClient.DTO.StudentDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class StudentsController {

    @FXML private TableView<StudentDTO> studentsTable;
    @FXML private TableColumn<StudentDTO, Long> idCol;
    @FXML private TableColumn<StudentDTO, String> nameCol;
    @FXML private TableColumn<StudentDTO, String> emailCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadStudents();
    }

    @FXML
    public void onRefresh() {
        loadStudents();
    }

    private void loadStudents() {
        try {
            String json = api.get("/voluntariado/students");
            if (json.startsWith("ERROR:")) {
                showError(json);
                return;
            }
            List<StudentDTO> students =
                    mapper.readValue(json, new TypeReference<List<StudentDTO>>() {});
            studentsTable.getItems().setAll(students);
        } catch (Exception e) {
            showError("Error loading students: " + e.getMessage());
        }
    }

    @FXML
    public void onAddStudent() {
        showInfo("TODO", "Abrir formulário para criar student (student-form.fxml).");
    }

    @FXML
    public void onEditStudent() {
        StudentDTO selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a student first.");
            return;
        }
        showInfo("TODO", "Abrir formulário para editar student: " + selected.getName());
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setTitle(title);
        a.showAndWait();
    }
}
