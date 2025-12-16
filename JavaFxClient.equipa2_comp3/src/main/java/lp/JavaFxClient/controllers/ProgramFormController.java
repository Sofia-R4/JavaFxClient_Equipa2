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
            String json = api.get("/types"); //get à api
            List<TypeDTO> list = //converte JSON em objetos Java
                    mapper.readValue(json, new TypeReference<List<TypeDTO>>() {});
            comboType.getItems().setAll(list); //obtém a lista na comboBox

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
        editingId = p.getId(); //guarda o id do programa
        txtName.setText(p.getNomeP()); //pega o nome e define como valor existente
        txtContact.setText(String.valueOf(p.getContact())); //pega o contacto e define como valor existente

        if (p.getType() == null) return; //se o programa nao tiver tipo, para

        comboType.getItems().stream() //percorre os itens do comboType
                .filter(t -> t.getType().equals(p.getType())) //filtra o item que tem o mesmo tipo que o programa
                .findFirst() //pega o primeiro
                .ifPresent(comboType::setValue); //define esse item como selecionado na combo
        
        if (p.getPartner() == null) return; //se o programa nao tiver parceiro, para

        comboPartner.getItems().stream() //percorre os itens do comboPartner
                .filter(t -> t.getPartner().equals(p.getPartner())) //filtra o item que tem o mesmo partner que o programa
                .findFirst() //pega o primeiro
                .ifPresent(comboPartner::setValue); //define esse item como selecionado na combo
    }

    @FXML
    public void onSave() {
        try {
            // TYPE
            TypeDTO selectedType = comboType.getValue(); //obtem o tipo selecionado na combo
            if (selectedType == null) { //se nenhum estiver selecionado
                showError("Select a type first."); //interrompe
                return;
            }

            // PARTNER
            PartnerDTO selectedPartner = comboPartner.getValue(); //obtem o partner selecionado na combo
            if (selectedPartner == null) { //se nenhum estiver selecionado
                showError("Select a partner first."); //interrompe
                return;
            }

            // Criar DTO para enviar à API
            ProgramDTO dto = new ProgramDTO();
            dto.setNomeP(txtName.getText());
            dto.setDescription(txtDescription.getText());
            dto.setLocation(txtLocation.getText());
            dto.setVagas(Integer.parseInt(txtVacancy.getText()));
            dto.setContact(Integer.parseInt(txtContact.getText()));

            // converte em strings (igual ao backend)
            dto.setType(selectedType.getType());
            dto.setPartner(selectedPartner.getPartner());

            // converte o ProgramDTO em JSON
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
            
            
            new Alert(Alert.AlertType.INFORMATION, result).showAndWait(); //mostra a mensagem da API
            txtName.getScene().getWindow().hide(); //fecha após guardar

        } catch (NumberFormatException e) { //utilizou texto em vez de numeros
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