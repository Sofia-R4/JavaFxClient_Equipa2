package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFxClient.DTO.ProgramDTO;
import lp.JavaFxClient.DTO.TypeDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class ProgramFormController {

    @FXML private TextField txtName;
    @FXML private TextField txtContact;
    @FXML private ComboBox<TypeDTO> comboType;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();
    private Long editingId = null; // null = criar | != null = editar

    /** Load all types from backend */
    @FXML
    public void initialize() {
        loadTypes();
    }

    private void loadTypes() {
        try {
            String json = api.get("/voluntariado/types");
            List<TypeDTO> list =
                    mapper.readValue(json, new TypeReference<List<TypeDTO>>() {});
            comboType.getItems().setAll(list);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading types: " + e.getMessage()).showAndWait();
        }
    }

    /** Fill form fields when editing */
    public void loadProgram(ProgramDTO p) {
        editingId = p.getId();
        txtName.setText(p.getNomeP());
        txtContact.setText(String.valueOf(p.getContact()));

        if (p.getType() == null) return;

        comboType.getItems().stream()
                .filter(t -> t.getType().equals(p.getType()))
                .findFirst()
                .ifPresent(comboType::setValue);
    }

    @FXML
    public void onSave() {
        try {
            // Verifica se algum type foi selecionado
            TypeDTO selectedType = comboType.getValue();
            if (selectedType == null) {
                showError("Select a type first.");
                return;
            }

            // Cria DTO do programa
            ProgramDTO dto = new ProgramDTO();
            dto.setNomeP(txtName.getText());
            dto.setContact(Integer.parseInt(txtContact.getText()));
            dto.setType(selectedType.getType()); // ou selectedType.getId() se a API espera ID

            // Converte DTO em JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);

            // Chama o POST na API
            String result = api.post("/voluntariado/programs", json);

            // Mostra resultado e fecha o form
            new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
            txtName.getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            showError("Contact must be a number.");
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }
    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    @FXML
    public void onCancel() {
        txtName.getScene().getWindow().hide();
    }
}
