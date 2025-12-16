package lp.JavaFxClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override  //subescrever um método de Application
    public void start(Stage stage) throws Exception { //stage janela principal da app e throws permite lançar exceções
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml")); //criar FXMLLoader responsável por carregar interfaces
        Scene scene = new Scene(loader.load()); //carrega o conteúdo e mostra (scene)

        stage.setTitle("Voluntariado"); //titulo da janela
        stage.setScene(scene); //associa a scene criada à stage

        
        stage.setWidth(500);   
        stage.setHeight(400);  

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
