package lp.JavaFxClient.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lp.JavaFxClient.services.ApiService;

public class LoginController {
		 
	    @FXML 
	    private TextField txtUser; 
	 
	    @FXML 
	    private PasswordField txtPass; 
	 
	    @FXML
	    private void onLogin() {
	        String username = txtUser.getText();
	        String password = txtPass.getText();

	        // Chamada ao backend
	        boolean success = ApiService.login(username, password);

	        if (success) {
	            openMain();
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText("Invalid Login");
	            alert.setContentText("Username or password is incorrect. Try again!");
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
