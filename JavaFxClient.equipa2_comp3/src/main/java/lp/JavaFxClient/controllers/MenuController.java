
package lp.JavaFxClient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private void onRegistarAdministrador() {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/RegistarAdministrador.fxml")
            );

            Stage stage = new Stage();
            stage.setTitle("Registar Administrador");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


