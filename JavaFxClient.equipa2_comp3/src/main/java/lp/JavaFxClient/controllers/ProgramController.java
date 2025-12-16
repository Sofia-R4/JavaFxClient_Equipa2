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
import lp.JavaFxClient.DTO.ProgramDTO;
import lp.JavaFxClient.services.ApiService;

import java.util.List;

public class ProgramController {

    @FXML private TableView<ProgramDTO> tablePrograms;
    @FXML private TableColumn<ProgramDTO, Long> idProg;
    @FXML private TableColumn<ProgramDTO, String> nameProg;
    @FXML private TableColumn<ProgramDTO, String> typeProg;
    @FXML private TableColumn<ProgramDTO, Integer> contactProg;
    @FXML private TableColumn<ProgramDTO, String> descriptionProg;
    @FXML private TableColumn<ProgramDTO, String> partnerProg;
    @FXML private TableColumn<ProgramDTO, Integer> vacancyProg;
    @FXML private TableColumn<ProgramDTO, String> locationProg;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() { //aparece nas colunas
        idProg.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProg.setCellValueFactory(new PropertyValueFactory<>("nomeP"));
        typeProg.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactProg.setCellValueFactory(new PropertyValueFactory<>("contact"));
        descriptionProg.setCellValueFactory(new PropertyValueFactory<>("description"));
        partnerProg.setCellValueFactory(new PropertyValueFactory<>("partner"));
        vacancyProg.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        locationProg.setCellValueFactory(new PropertyValueFactory<>("location"));

        loadPrograms();
    }

    @FXML
    public void onRefresh() {
        loadPrograms();
    }

    private void loadPrograms() {
        try {
            String json = api.get("/programs");  //get à api
            if (json.startsWith("ERROR:")) { //verifica se a API devolveu uma mensagem de erro
                showError(json); //mostra a sms de erro 
                return;
            }

            List<ProgramDTO> programs = //conversão de JSON para objetos Java
                    mapper.readValue(json, new TypeReference<List<ProgramDTO>>() {});
            tablePrograms.getItems().setAll(programs); //a tabela atualiza automaticamente

        } catch (Exception e) {
            showError("Error loading programs: " + e.getMessage());
        }
    }
    @FXML
    public void onAddProgram() {
        openProgramForm(null); //abre um formulário em criação (sem nada, null)
    }

    @FXML
    public void onEditProgram() {
        ProgramDTO selected = tablePrograms.getSelectionModel().getSelectedItem(); //acede ao SelectionModel da tabela e obtem o item atualmente selecionado
        if (selected == null) { //se nao for selecionado nada
            showError("Select a program first."); //retorna
            return;
        }
        openProgramForm(selected); //se não abre o formulário
    }

    private void openProgramForm(ProgramDTO program) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/program-form.fxml")); //carrega o ficheiro do FXML do formulário
            Parent root = loader.load();

            ProgramFormController controller = loader.getController(); //obtenho o controller do form (buscar métodos)
            if (program != null) //se o parceiro não estiver vazio, 
                controller.loadProgram(program); //guarda os dados inseridos

            Stage stage = new Stage(); //cria uma nova janela
            stage.initModality(Modality.APPLICATION_MODAL); //define a janela como Modal (nao se pode interagir)
            stage.setScene(new Scene(root)); //associa a interface carregada à nova janela
            stage.setTitle(program == null ? "Add Program" : "Edit Program"); //se partner=nul, adiciona, se não edita
            stage.showAndWait(); // nao bloqueia e espera até a janela fechar

            loadPrograms(); //atualiza os dados

        } catch (Exception e) {
            showError("Cannot open form: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg); //cria um alerta
        a.setTitle(title); //define o titulo da janela
        a.showAndWait(); //nao bloqueia e espera até a janela fechar
    }    
}
