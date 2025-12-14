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
        editingId = s.getId(); 
        formTitle.setText("Edit Student"); 
        txtName.setText(s.getName()); 
        txtEmail.setText(s.getEmail());
    } 
 
    @FXML
    public void onSave() { 
        try { 
            StudentDTO dto = new StudentDTO(); 
            dto.setName(txtName.getText()); 
            dto.setEmail(txtEmail.getText());
            dto.setNum(Integer.parseInt(txtNum.getText())); // agora envia num
            dto.setPassword(txtPass.getText());

            // Converter DTO para JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);

            // Chamar o POST no ApiService
            String result = api.post("/students/criar/student", json);

            new Alert(Alert.AlertType.INFORMATION, result).showAndWait(); 
            txtName.getScene().getWindow().hide(); 

        } catch (Exception e) { 
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait(); 
        }
    }
  
     @FXML 
     public void onCancel() { 
         txtName.getScene().getWindow().hide(); 
     } 
}
