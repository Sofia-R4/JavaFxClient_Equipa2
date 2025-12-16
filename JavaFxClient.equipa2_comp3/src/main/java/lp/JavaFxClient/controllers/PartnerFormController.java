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
        editingId = p.getId(); //chama o método getId e guarda em editingId
        txtPartner.setText(p.getPartner()); //nome que define o texto de campo do formulário
    }

    @FXML
    public void onSave() {
        try {
            PartnerDTO dto = new PartnerDTO(); //crio um novo objeto DTO que é enviado para a API
            dto.setPartner(txtPartner.getText()); //copia os dados da interface para o objeto java

            String json = mapper.writeValueAsString(dto); //converte DTO em JSON

            String result;
            if (editingId == null) { //se for =null
                result = api.post("/partners", json); //cria um novo
            } else {
                result = api.put("/partners/by-name/" + editingId, json); //atualiza
            }

            new Alert(Alert.AlertType.INFORMATION, result).showAndWait(); //cria uma alerta e mostra a mensagem devolvida pela API
            txtPartner.getScene().getWindow().hide(); //o fomrulário fecha depois de ser salvo

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onCancel() {
        txtPartner.getScene().getWindow().hide();
    }
}
