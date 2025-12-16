package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lp.JavaFxClient.DTO.StudentDTO;
import lp.JavaFxClient.services.ApiService;

public class StudentFormController {
	 
    @FXML private Label formTitle; 
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNum;
    @FXML private TextField txtPass; 
 
 
    private final ApiService api = new ApiService(); 
    private Long editingId = null; 
 
    public void loadStudent(StudentDTO s) { 
        editingId = s.getId();  //guarda o id do estudante
        formTitle.setText("Edit Student"); 
        txtName.setText(s.getName());  //pega o nome e define
        txtEmail.setText(s.getEmail()); //pega o email e define
    } 
 
    @FXML
    public void onSave() { 
        try { 
        	// Criar DTO para enviar à API
            StudentDTO dto = new StudentDTO(); 
            dto.setName(txtName.getText()); 
            dto.setEmail(txtEmail.getText());
            dto.setNum(Integer.parseInt(txtNum.getText())); // agora envia num
            dto.setPassword(txtPass.getText());

            // converte o ProgramDTO em JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);

            // Chamar o POST no ApiService
            String result = api.post("/students/criar/student", json);

            new Alert(Alert.AlertType.INFORMATION, result).showAndWait(); //mostra a mensagem na API
            txtName.getScene().getWindow().hide();  //fecha após guardar

        } catch (Exception e) { 
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait(); 
        }
    }
  
     @FXML 
     public void onCancel() { 
         txtName.getScene().getWindow().hide(); 
     } 
}
