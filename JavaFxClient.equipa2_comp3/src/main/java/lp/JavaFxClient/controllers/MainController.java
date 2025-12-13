package lp.JavaFxClient.controllers;

/**
 * Este controller gere apenas navegação: carrega FXMLs e mete-os no centro.
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {
        // Ao iniciar, mostra logo a lista de estudantes, por exemplo
        showStudentsView();
    }

    @FXML
    public void showStudentsView() {
        loadView("/students-view.fxml");
    }

    @FXML
    public void showProgramsView() {
        loadView("/program-view.fxml");
    }

    @FXML
    public void showTypesView() {
        loadView("/type-view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();

            contentPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "Error loading view: " + e.getMessage());
            a.showAndWait();
        }
    }

    @FXML
    public void onExit() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.close();
    }
}