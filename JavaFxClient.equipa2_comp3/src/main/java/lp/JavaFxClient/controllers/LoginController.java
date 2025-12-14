package lp.JavaFxClient.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
		 
	    @FXML 
	    private TextField txtUser; 
	 
	    @FXML 
	    private PasswordField txtPass; 
	 
	    @FXML 
	    private void onLogin() { 
	        String user = txtUser.getText(); 
	        String pass = txtPass.getText(); 
	 
	        if (user.equals("Francisca") && pass.equals("francisca123")) { 
	            openMain(); 
	        } else { 
	            Alert alert = new Alert(Alert.AlertType.ERROR); 
	            alert.setHeaderText("Invalid Login"); 
	            alert.setContentText("try Again!"); 
	            alert.show(); 
	        } 
	    } 
	 
	    private void openMain() { 
	        try { 
	            FXMLLoader loader = new 
	FXMLLoader(getClass().getResource("/main-view.fxml")); 
	            Parent root = loader.load(); 
	 
	            Stage stage = new Stage(); 
	            stage.setTitle("Main Menu"); 
	            stage.setScene(new Scene(root)); 
	            stage.show(); 
	 
	            // Fechar janela de login 
	            Stage janelaLogin = (Stage) txtUser.getScene().getWindow(); 
	            janelaLogin.close(); 
	 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	    } 
}

//recup
