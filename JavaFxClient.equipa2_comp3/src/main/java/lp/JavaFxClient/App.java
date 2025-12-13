package lp.JavaFxClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
	    Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
	    stage.setTitle("Menu Principal");
	    stage.setScene(new Scene(root));
	    stage.show();
	}


    public static void main(String[] args) {
        launch(args); // inicializa o JavaFX
    }
}
