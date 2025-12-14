package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFxClient.DTO.PartnerDTO;
import lp.JavaFxClient.DTO.ProgramDTO;
import lp.JavaFxClient.DTO.TypeDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class ProgramFormController {

    @FXML private TextField txtName;
    @FXML private TextField txtContact;
    @FXML private ComboBox<TypeDTO> comboType;
    @FXML private TextField txtDescription;
    @FXML private TextField txtLocation;
    @FXML private TextField txtVacancy;
    @FXML private ComboBox<PartnerDTO> comboPartner;


    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();
    private Long editingId = null; // null = criar | != null = editar

    /** Load all types from backend */
    @FXML
    public void initialize() {
        loadTypes();
        loadPartners();
    }

    private void loadTypes() {
        try {
            String json = api.get("/types");
            List<TypeDTO> list =
                    mapper.readValue(json, new TypeReference<List<TypeDTO>>() {});
            comboType.getItems().setAll(list);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading types: " + e.getMessage()).showAndWait();
        }
    }
    
    private void loadPartners() {
        try {
            String json = api.get("/partners");
            List<PartnerDTO> list =
                    mapper.readValue(json, new TypeReference<List<PartnerDTO>>() {});
            comboPartner.getItems().setAll(list);

        } catch (Exception e) {
        	new Alert(Alert.AlertType.ERROR, "Error loading partners: " + e.getMessage()).showAndWait();
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
        
        if (p.getPartner() == null) return;

        comboPartner.getItems().stream()
                .filter(t -> t.getPartner().equals(p.getPartner()))
                .findFirst()
                .ifPresent(comboPartner::setValue);
    }

    @FXML
    public void onSave() {
        try {
            // TYPE
            TypeDTO selectedType = comboType.getValue();
            if (selectedType == null) {
                showError("Select a type first.");
                return;
            }

            // PARTNER
            PartnerDTO selectedPartner = comboPartner.getValue();
            if (selectedPartner == null) {
                showError("Select a partner first.");
                return;
            }

            // Criar DTO
            ProgramDTO dto = new ProgramDTO();
            dto.setNomeP(txtName.getText());
            dto.setDescription(txtDescription.getText());
            dto.setLocation(txtLocation.getText());
            dto.setVagas(Integer.parseInt(txtVacancy.getText()));
            dto.setContact(Integer.parseInt(txtContact.getText()));

            // enviar strings (igual ao backend)
            dto.setType(selectedType.getType());
            dto.setPartner(selectedPartner.getPartner());

            // JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);
            String result;

            if (editingId == null) {
                // Criar novo programa
                result = api.post("/programs", json);
            } else {
                // Editar programa existente
                result = api.put("/programs/" + editingId, json);
            }
            
            
            new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
            txtName.getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            showError("Vacancy and Contact must be numbers.");
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