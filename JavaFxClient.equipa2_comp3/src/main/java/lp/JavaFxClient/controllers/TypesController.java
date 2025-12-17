package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.TypeDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class TypesController {

    @FXML private TableView<TypeDTO> tableTypes;
    @FXML private TableColumn<TypeDTO, Long> idType;
    @FXML private TableColumn<TypeDTO, String> nameType;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idType.setCellValueFactory(new PropertyValueFactory<>("id")); //aparece o dado "id" na coluna
        nameType.setCellValueFactory(new PropertyValueFactory<>("type")); //aparece o dado "partner" na coluna
        loadTypes(); //chama o método 
    }

    private void loadTypes() {
        try {
            String json = api.get("/types"); //get à api

            List<TypeDTO> list =  //conversão de JSON para objetos Java
            		mapper.readValue(json, new TypeReference<List<TypeDTO>>() {});
            tableTypes.getItems().setAll(list); //a tabela atualiza

        } catch (Exception e) {
            showError("Error loading types: " + e.getMessage());
        }
    }


    @FXML
    public void onRefresh() {
        loadTypes();
    }

    @FXML
    public void onAddType() {
        openForm(null); //abre um formulário em criação (sem nada, null)
    }

    @FXML
    public void onEditType() {
    	//acede ao SelectionModel da tabela e obtem o item atualmente selecionado
        TypeDTO t = tableTypes.getSelectionModel().getSelectedItem();
        if (t == null) { 
            showError("Select a type first.");
            return;
        }
        openForm(t);
    }

    private void openForm(TypeDTO type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/type-form.fxml")); //carrega o ficheiro do FXML do formulário
            Parent root = loader.load(); //classe base do javaFX, nó da raiz, lê o arquivo e cria as comp gráficas 

            TypeFormController controller = loader.getController(); //obtenho o controller do form (buscar métodos)
            if (type != null)  //se o parceiro não estiver vazio, 
            	controller.loadType(type);  //guarda os dados inseridos

            Stage stage = new Stage(); //cria uma nova janela
            stage.initModality(Modality.APPLICATION_MODAL); //define a janela como Modal (nao se pode interagir)
            stage.setScene(new Scene(root)); //associa a interface carregada à nova janela
            stage.setTitle(type == null ? "Add Type" : "Edit Type"); //se type=nul, adiciona, se não edita
            stage.showAndWait(); //nao bloqueia e espera até a janela fechar

            loadTypes(); //atualiza os dados
            
        } catch (Exception e) {
            showError("Cannot open form: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
