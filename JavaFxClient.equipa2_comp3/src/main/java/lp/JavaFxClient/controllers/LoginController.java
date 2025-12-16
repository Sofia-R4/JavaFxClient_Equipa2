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
	        String email = txtUser.getText();
	        String password = txtPass.getText();

	        // Chamada ao backend
	        boolean success = ApiService.login(email, password);

	        if (success) {
	            openMain();
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText("Invalid Login");
	            alert.setContentText("Email or password is incorrect. Try again!");
	            alert.show();
	        }
	    }
	 
	    private void openMain() { 
	        try { 
	            FXMLLoader loader = new 
	FXMLLoader(getClass().getResource("/main-view.fxml")); //procura o arquivo FXML
	            Parent root = loader.load();  //carrega no objeto Parent, root é o nó principal
	 
	            Stage stage = new Stage(); //cria uma nova janela
	            stage.setTitle("Main Menu"); 
	            stage.setScene(new Scene(root)); //cria uma cena usando o layout de root
	            stage.show(); 
	 
	            
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	    } 
}
