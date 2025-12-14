package lp.JavaFxClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Voluntariado");
        stage.setScene(scene);

        // Set preferred window size
        stage.setWidth(500);   // or 600, 800, whatever you prefer
        stage.setHeight(400);  // optional

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
