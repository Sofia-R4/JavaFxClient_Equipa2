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

    
    public void loadType(TypeDTO t) { //carrega os tipos
        editingId = t.getId(); //chama o método getId e guarda em editingId
        txtType.setText(t.getType()); //nome que define o texto de campo do formulário
    }

    @FXML
    public void onSave() {
        try { 
            TypeDTO dto = new TypeDTO(); //crio um novo objeto DTO que é enviado para a API
            dto.setType(txtType.getText()); //copia os dados da interface para o objeto java

            String json = mapper.writeValueAsString(dto); //converte DTO em JSON

            String result;
            if (editingId == null) { //se for =null
                result = api.post("/types", json); // cria
            } else {
                result = api.put("/types/by-name/" + editingId, json); //edita
            }

            new Alert(Alert.AlertType.INFORMATION, result).showAndWait(); //cria uma alerta e mostra a mensagem devolvida pela API
            txtType.getScene().getWindow().hide(); //o fomrulário fecha depois de ser salvo

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }


    @FXML
    public void onCancel() {
        txtType.getScene().getWindow().hide();
    }
}
