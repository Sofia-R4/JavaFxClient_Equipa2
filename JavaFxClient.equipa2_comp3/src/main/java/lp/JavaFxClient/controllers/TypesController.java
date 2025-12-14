package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.TypeDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class TypesController {

    @FXML private TableView<TypeDTO> tableTypes;
    @FXML private TableColumn<TypeDTO, Long> idType;
    @FXML private TableColumn<TypeDTO, String> nameType;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idType.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameType.setCellValueFactory(new PropertyValueFactory<>("type"));
        loadTypes();
    }

    private void loadTypes() {
        try {
            String json = api.get("/types");
            System.out.println(json);

            if (!json.trim().startsWith("[")) {
                showError("API returned error:\n" + json);
                return;
            }

            List<TypeDTO> list = mapper.readValue(
                    json,
                    new TypeReference<List<TypeDTO>>() {}
            );

            tableTypes.getItems().setAll(list);

        } catch (Exception e) {
            showError("Error loading types: " + e.getMessage());
        }
    }


    @FXML
    public void onRefresh() {
        loadTypes();
    }

    @FXML
    public void onAddType() {
        openForm(null);
    }

    @FXML
    public void onEditType() {
        TypeDTO t = tableTypes.getSelectionModel().getSelectedItem();
        if (t == null) {
            showError("Select a type first.");
            return;
        }
        openForm(t);
    }

    @FXML
    public void onDelete() {
        TypeDTO t = tableTypes.getSelectionModel().getSelectedItem();
        if (t == null) {
            showError("Select a type first.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete type " + t.getType() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();
        if (confirm.getResult() != ButtonType.YES) return;

        String result = api.delete("/types/" + t.getId());
        new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
        loadTypes();
    }

    private void openForm(TypeDTO type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/type-form.fxml"));
            Parent root = loader.load();

            TypeFormController controller = loader.getController();
            if (type != null) controller.loadType(type);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle(type == null ? "Add Type" : "Edit Type");
            stage.showAndWait();

            loadTypes();
        } catch (Exception e) {
            showError("Cannot open form: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
