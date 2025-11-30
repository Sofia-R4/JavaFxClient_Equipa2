package upt.lp.equipa2_comp2.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import upt.lp.equipa2_comp2.dto.ProgramDTO;

import java.util.Scanner;


	public class MainClient {
		private static final String BASE_URL = "http://localhost:8080";
		private static final RestTemplate rest = new RestTemplate();
		private static final Scanner sc = new Scanner(System.in);
		
		public static void main(String[] args) {
			while (true) {
				System.out.println("\n");
				System.out.println("1 - Criar admin");
				System.out.println("2 - Criar estudante");
				System.out.println("3 - Criar programa");			
				System.out.println("4 - Criar Type");
				System.out.println("5 - Listar programas");
				System.out.println("6 - Listar users");
				System.out.println("7 - Listar students");
				System.out.println("8 - Listar types");
				System.out.println("0 - Sair");
				
				int option = Integer.parseInt(sc.nextLine());
				
				switch (option) {
				case 1 -> createAdmin();				
				case 2 -> createStudent(); 
				case 3 -> createProgram(); 
				case 4 -> createType();
				case 5 -> listPrograms();
				case 6 -> listUsers();
				case 7 -> listStudents();
				case 8 -> listTypes();
				 
				case 0 -> {
					System.out.println("A sair...");
					return;
				}
				
				default -> System.out.println("Opção inválida!");
				
				}
			}

		}
		
		private static void createAdmin() {
		    System.out.print("Nome: ");
		    String name = sc.nextLine();

		    System.out.print("E-mail: ");
		    String email = sc.nextLine();

		    System.out.print("Password: ");
		    String password = sc.nextLine();

		    String json = """
		    {
		      "name": "%s",
		      "email": "%s",
		      "password": "%s"
		    }
		    """.formatted(name, email, password);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> request = new HttpEntity<>(json, headers);
		    String response = rest.postForObject(BASE_URL + "/voluntariado/users/admin", request, String.class);
		    System.out.println(response);
		    }
		
		private static void createStudent() {
		    System.out.print("Nome: ");
		    String name = sc.nextLine();

		    System.out.print("E-mail: ");
		    String email = sc.nextLine();

		    System.out.print("Password: ");
		    String password = sc.nextLine();
		    
		    System.out.println("Número de aluno");
		    int num = Integer.parseInt(sc.nextLine());

		    String json = """
		    {
		      "name": "%s",
		      "email": "%s",
		      "password": "%s",
		      "num": "%s"
		    }
		    """.formatted(name, email, password, num);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> request = new HttpEntity<>(json, headers);	
		    String response = rest.postForObject(BASE_URL + "/voluntariado/students/student", request, String.class);
		    System.out.println(response);
		    }
		
		private static void createProgram() {
		    System.out.print("Nome: ");
		    String nomeP = sc.nextLine();

		    System.out.print("Descrição: ");
		    String description = sc.nextLine();

		    System.out.print("Localização: ");
		    String location = sc.nextLine();
		    
		    System.out.print("Contacto: ");
		    int contact = sc.nextInt();
		    
		    System.out.print("Número de vagas disponiveis: ");
		    int vagas = sc.nextInt();
		    sc.nextLine();
		    
		    System.out.print("Partner: ");
		    String partner = sc.nextLine();
		    
		    System.out.print("Type: ");
		    String type = sc.nextLine();
		    
		    String json = """
		    {
		      "nomeP": "%s",
		      "description": "%s",
		      "location": "%s",
		      "contact": "%s",
		      "vagas": "%s",
		      "partner": "%s",
		      "type": "%s"
		    }
		    """.formatted(nomeP, description, location, contact, vagas, partner, type);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> request = new HttpEntity<>(json, headers);	
		    String response = rest.postForObject(BASE_URL + "/voluntariado/programs", request, String.class);
		    System.out.println(response);
		    }
		
		private static void createType() {
		    System.out.print("Nome: ");
		    String type = sc.nextLine();

		    String json = """
		    {
		      "type": "%s"
		    }
		    """.formatted(type);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> request = new HttpEntity<>(json, headers);
		    String response = rest.postForObject(BASE_URL + "/voluntariado/type", request, String.class);
		    System.out.println(response);
		    }
		
		private static void listPrograms() {
			String url = BASE_URL + "/voluntariado/programs";
			
			try {
				String response = rest.getForObject(url, String.class);
				System.out.println(response);
				 
			} catch(Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		
		private static void listUsers() {
			String url = BASE_URL + "/voluntariado/users";
			
			try {
				String response = rest.getForObject(url, String.class);
				System.out.println(response);
				 
			} catch(Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		
		private static void listStudents() {
			String url = BASE_URL + "/voluntariado/students";
			
			try {
				String response = rest.getForObject(url, String.class);
				System.out.println(response);
				 
			} catch(Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		
		private static void listTypes() {
			String url = BASE_URL + "/voluntariado/types";
			
			try {
				String response = rest.getForObject(url, String.class);
				System.out.println(response);
				 
			} catch(Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
				
	}
	
