package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.ProgramDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class ProgramController {

    @FXML private TableView<ProgramDTO> tablePrograms;
    @FXML private TableColumn<ProgramDTO, Long> idProg;
    @FXML private TableColumn<ProgramDTO, String> nameProg;
    @FXML private TableColumn<ProgramDTO, String> typeProg;
    @FXML private TableColumn<ProgramDTO, Integer> contactProg;
    @FXML private TableColumn<ProgramDTO, String> descriptionProg;
    @FXML private TableColumn<ProgramDTO, String> partnerProg;
    @FXML private TableColumn<ProgramDTO, Integer> vacancyProg;
    @FXML private TableColumn<ProgramDTO, String> locationProg;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idProg.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProg.setCellValueFactory(new PropertyValueFactory<>("nomeP"));
        typeProg.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactProg.setCellValueFactory(new PropertyValueFactory<>("contact"));
        descriptionProg.setCellValueFactory(new PropertyValueFactory<>("description"));
        partnerProg.setCellValueFactory(new PropertyValueFactory<>("partner"));
        vacancyProg.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        locationProg.setCellValueFactory(new PropertyValueFactory<>("location"));

        loadPrograms();
    }

    @FXML
    public void onRefresh() {
        loadPrograms();
    }

    private void loadPrograms() {
        try {
            String json = api.get("/programs"); 
            if (json.startsWith("ERROR:")) {
                showError(json);
                return;
            }

            List<ProgramDTO> programs =
                    mapper.readValue(json, new TypeReference<List<ProgramDTO>>() {});
            tablePrograms.getItems().setAll(programs);

        } catch (Exception e) {
            showError("Error loading programs: " + e.getMessage());
        }
    }
    @FXML
    public void onAddProgram() {
        openProgramForm(null);
    }

    @FXML
    public void onEditProgram() {
        ProgramDTO selected = tablePrograms.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a program first.");
            return;
        }
        openProgramForm(selected);
    }

    private void openProgramForm(ProgramDTO program) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/program-form.fxml"));
            Parent root = loader.load();

            ProgramFormController controller = loader.getController();
            if (program != null)
                controller.loadProgram(program);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle(program == null ? "Add Program" : "Edit Program");
            stage.showAndWait();

            loadPrograms();

        } catch (Exception e) {
            showError("Cannot open form: " + e.getMessage());
        }
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
