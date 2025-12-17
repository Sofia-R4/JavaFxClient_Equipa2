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
import lp.JavaFxClient.DTO.StudentDTO;
import lp.JavaFxClient.services.ApiService;

import java.io.IOException;
import java.util.List;

public class StudentsController {

	@FXML
	private TableView<StudentDTO> studentsTable;
	@FXML
	private TableColumn<StudentDTO, Integer> numCol;
	@FXML
	private TableColumn<StudentDTO, String> nameCol;
	@FXML
	private TableColumn<StudentDTO, String> emailCol;


	private final ApiService api = new ApiService();
	private final ObjectMapper mapper = new ObjectMapper();

	@FXML
	public void initialize() {
		//aparece nas colunas
	    numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
	    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

	    loadStudents(); //chama o método 
	}

	@FXML
	public void onRefresh() {
		loadStudents();
	}

	private void loadStudents() {
	    try {
	        String json = api.get("/students"); // backend retorna StudentResponseDTO, faz get à api
	        List<StudentDTO> students = mapper.readValue(json, new TypeReference<List<StudentDTO>>() {});
	        studentsTable.getItems().setAll(students); //conversão de json para objetos java
	    } catch (Exception e) {
	        showError("Error loading students: " + e.getMessage());
	    }
	}

	

	@FXML
	public void onAddStudent() {
		// showInfo("TODO", "Abrir formulário para criar student (student-form.fxml).");

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/student-form.fxml")); //carrega o ficheiro do FXML do formulário
			Parent root = loader.load(); //classe base do javaFX, nó da raiz, lê o arquivo e cria as comp gráficas 

			Stage stage = new Stage(); //cria uma nova janela
			stage.setTitle("Create Student");
			stage.setScene(new Scene(root)); //associa a interface carregada à nova janela
			stage.initModality(Modality.APPLICATION_MODAL); //define a janela como Modal (nao se pode interagir)
			stage.showAndWait(); // nao bloqueia e espera até a janela fechar

			loadStudents(); // refresca tabela após fechar

		} catch (IOException e) {
			e.printStackTrace();
			showError("Error opening student form.");
		}

	}

	@FXML
	public void onEditStudent() {
		//acede ao SelectionModel da tabela e obtem o item atualmente selecionado
		StudentDTO selected = studentsTable.getSelectionModel().getSelectedItem(); 
		if (selected == null) { //se nao for selecionado nada
			showError("Select a student first.");
			return;
		}
		showInfo("TODO", "Open form to edit student: " + selected.getName());
	}

	private void showError(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR, msg);
		a.showAndWait();
	}

	private void showInfo(String title, String msg) {
		Alert a = new Alert(Alert.AlertType.INFORMATION, msg); //cria um alerta
		a.setTitle(title); //define o titulo da janela
		a.showAndWait();  //nao bloqueia e espera até a janela fechar
	}
}
