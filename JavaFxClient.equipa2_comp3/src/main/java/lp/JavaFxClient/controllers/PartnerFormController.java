package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.PartnerDTO;
import lp.JavaFxClient.services.ApiService;

public class PartnerFormController {

    @FXML private TextField txtPartner;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();
    private Long editingId = null;

    /** Preenche o formulário ao editar */
    public void loadPartner(PartnerDTO p) {
        editingId = p.getId();
        txtPartner.setText(p.getPartner());
    }

    @FXML
    public void onSave() {
        try {
            PartnerDTO dto = new PartnerDTO();
            dto.setPartner(txtPartner.getText());

            String json = mapper.writeValueAsString(dto);

            String result;
            if (editingId == null) {
                result = api.post("/partners", json);
            } else {
                result = api.put("/partners/by-name/" + editingId, json);
            }

            new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
            txtPartner.getScene().getWindow().hide();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onCancel() {
        txtPartner.getScene().getWindow().hide();
    }
}
