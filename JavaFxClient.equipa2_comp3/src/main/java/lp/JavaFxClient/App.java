package lp.JavaFxClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o FXML
        Parent root = FXMLLoader.load(getClass().getResource("/menuEstudante.fxml"));
        
        // Cria a cena
        Scene scene = new Scene(root);
        
        // Configura a janela
        primaryStage.setTitle("Perfil Estudante");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // inicializa o JavaFX
    }
}
