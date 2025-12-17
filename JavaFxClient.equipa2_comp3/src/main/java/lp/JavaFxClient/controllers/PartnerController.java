package lp.JavaFxClient.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.JavaFxClient.DTO.PartnerDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class PartnerController {

    @FXML private TableView<PartnerDTO> tablePartners;
    @FXML private TableColumn<PartnerDTO, Long> idCol;
    @FXML private TableColumn<PartnerDTO, String> partnerCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); //aparece o dado "id" na coluna
        partnerCol.setCellValueFactory(new PropertyValueFactory<>("partner")); //aparece o dado "partner" na coluna
        loadPartners(); //chama o método 
    }

    @FXML
    public void onRefresh() {
        loadPartners();
    }

    private void loadPartners() {
        try {
            String json = api.get("/partners"); //get à api
            List<PartnerDTO> partners =  //conversão de JSON para objetos Java
                    mapper.readValue(json, new TypeReference<List<PartnerDTO>>() {});
            tablePartners.getItems().setAll(partners); //a tabela atualiza automaticamente

        } catch (Exception e) {
            showError("Error loading partners: " + e.getMessage());
        }
    }

    @FXML
    public void onAddPartner() {
        openPartnerForm(null); //abre um formulário em criação (sem nada, null)
    }

    @FXML
    public void onEditPartner() {
    	//acede ao SelectionModel da tabela e obtem o item atualmente selecionado
        PartnerDTO selected = tablePartners.getSelectionModel().getSelectedItem(); 
        if (selected == null) { //se nao for selecionado nada
            showError("Select a partner first."); //retorna
            return;
        }
        openPartnerForm(selected); //se não abre o formulário
    }

    private void openPartnerForm(PartnerDTO partner) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/partner-form.fxml")); //carrega o ficheiro do FXML do formulário
            Parent root = loader.load(); //classe base do javaFX, nó da raiz, lê o arquivo e cria as comp gráficas 

            PartnerFormController controller = loader.getController(); //obtenho o controller do form (buscar métodos)
            if (partner != null) //se o parceiro não estiver vazio, 
                controller.loadPartner(partner); //guarda os dados inseridos

            Stage stage = new Stage(); //cria uma nova janela
            stage.initModality(Modality.APPLICATION_MODAL); //define a janela como Modal (nao se pode interagir)
            stage.setScene(new Scene(root)); //associa a interface carregada à nova janela
            stage.setTitle(partner == null ? "Add Partner" : "Edit Partner"); //se partner=nul, adiciona, se não edita
            stage.showAndWait(); // nao bloqueia e espera até a janela fechar

            loadPartners(); //atualiza os dados

        } catch (Exception e) {
            showError("Cannot open partner form: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
