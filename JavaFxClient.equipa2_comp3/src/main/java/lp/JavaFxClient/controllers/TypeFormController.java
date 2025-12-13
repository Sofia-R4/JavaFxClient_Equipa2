package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.TypeDTO;
import lp.JavaFxClient.services.ApiService;

public class TypeFormController {

    @FXML private TextField txtType;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();
    private Long editingId = null;

    /** Preenche o formulário ao editar */
    public void loadType(TypeDTO t) {
        editingId = t.getId();
        txtType.setText(t.getType());
    }

    @FXML
    public void onSave() {
        try {
            // Cria DTO do type
            TypeDTO dto = new TypeDTO();
            dto.setType(txtType.getText());

            // Converte DTO em JSON
            String json = mapper.writeValueAsString(dto);

            // Chama o POST na API para criar novo type
            String result = api.post("/voluntariado/types", json);

            // Mostra resultado e fecha o form
            new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
            txtType.getScene().getWindow().hide();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onCancel() {
        txtType.getScene().getWindow().hide();
    }
}

