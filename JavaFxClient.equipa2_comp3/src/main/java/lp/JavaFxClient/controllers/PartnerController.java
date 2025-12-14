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
import lp.JavaFxClient.DTO.PartnerDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class PartnerController {

    @FXML private TableView<PartnerDTO> tablePartners;
    @FXML private TableColumn<PartnerDTO, Long> idCol;
    @FXML private TableColumn<PartnerDTO, String> partnerCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partnerCol.setCellValueFactory(new PropertyValueFactory<>("partner"));
        loadPartners();
    }

    @FXML
    public void onRefresh() {
        loadPartners();
    }

    private void loadPartners() {
        try {
            String json = api.get("/partners");
            List<PartnerDTO> partners =
                    mapper.readValue(json, new TypeReference<List<PartnerDTO>>() {});
            tablePartners.getItems().setAll(partners);

        } catch (Exception e) {
            showError("Error loading partners: " + e.getMessage());
        }
    }

    @FXML
    public void onAddPartner() {
        openPartnerForm(null);
    }

    @FXML
    public void onEditPartner() {
        PartnerDTO selected = tablePartners.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a partner first.");
            return;
        }
        openPartnerForm(selected);
    }

    private void openPartnerForm(PartnerDTO partner) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/partner-form.fxml"));
            Parent root = loader.load();

            PartnerFormController controller = loader.getController();
            if (partner != null)
                controller.loadPartner(partner);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle(partner == null ? "Add Partner" : "Edit Partner");
            stage.showAndWait();

            loadPartners();

        } catch (Exception e) {
            showError("Cannot open partner form: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
