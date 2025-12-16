package lp.JavaFxClient.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
	private static final String BASE_URL = "http://localhost:8080/voluntariado";
	private final HttpClient client = HttpClient.newHttpClient();

    public String get(String path) {
        try {
            HttpRequest request = HttpRequest.newBuilder() //cria um http request usando a API
                    .uri(URI.create(BASE_URL + path)) //endpoint 
                    .GET() //define que é get
                    .build(); //cria o objeto request
            
            HttpResponse<String> response = //envia a requisição e aguarda a resposta
                    client.send(request, HttpResponse.BodyHandlers.ofString()); //Body - indica como o corpo da resposta deve ser processado (string)

            
            if (response.statusCode() >= 400) { //se for 400 ou maior indica o erro
                return "ERROR (" + response.statusCode() + "): " + response.body();
            }

            return response.body(); //retorna resposta da API

        } catch (Exception e) {
            e.printStackTrace(); 
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }

    public String post(String path, String json) {
        try {
            HttpRequest.BodyPublisher body = //define o corpo da requisição 
                    (json == null || json.isEmpty()) //se json for null ou vazio
                            ? HttpRequest.BodyPublishers.noBody() //envia nenhum corpo
                            : HttpRequest.BodyPublishers.ofString(json); //se não envia como uma string

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path)) //URI da api 
                    .header("Content-Type", "application/json") //informa que o corpo é JSON
                    .POST(body) //define que é post
                    .build(); //cria o request

            HttpResponse<String> response = //envia a requisição e aguarda a resposta
                    client.send(request, HttpResponse.BodyHandlers.ofString()); //Body - indica como o corpo da resposta deve ser processado (string)

            if (response.statusCode() >= 400) { //se for 400 ou maior indica o erro
                return "ERROR (" + response.statusCode() + "): " + response.body();
            }

            return response.body(); //retorna resposta da API

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }

    
    public String put(String path, String json) {
        try {
            HttpRequest.BodyPublisher body = //define o corpo da requisição 
                    (json == null || json.isEmpty()) //se json for null ou vazio
                            ? HttpRequest.BodyPublishers.noBody() //envia nenhum corpo
                            : HttpRequest.BodyPublishers.ofString(json); //se não envia como uma string

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path)) //URI da api 
                    .header("Content-Type", "application/json") //informa que o corpo é JSON
                    .PUT(body) //define que é put
                    .build(); //cria o request
 
            HttpResponse<String> response = //envia a requisição e aguarda a resposta
                    client.send(request, HttpResponse.BodyHandlers.ofString()); //Body - indica como o corpo da resposta deve ser processado (string)

            if (response.statusCode() >= 400) {
                return "ERROR (" + response.statusCode() + "): " + response.body();
            }

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }
    
    public String delete(String path) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path)) //URI da api
                    .DELETE() //define que é delete
                    .build(); //cria o request

            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body(); //envia a requesição HTTP, o corpo deve ser lido como uma string
            return body == null ? "" : body; //verifica se é null, se sim, retorna "" se não o corpo da resposta

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }
    //metodo de login a partir dum endpoint e devolve 200 ou erro
    public static boolean login(String email, String password) {

        try {
            HttpClient client = HttpClient.newHttpClient(); //cria um cliente http que será usad para a requisição

          //string json com os dados do login
            String json = """ 
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(email, password);

            HttpRequest request = HttpRequest.newBuilder() //cria a requisição post para o endpoint
                    .uri(URI.create(BASE_URL + "/users/login"))
                    .header("Content-Type", "application/json") //define como json
                    .POST(HttpRequest.BodyPublishers.ofString(json)) //envia
                    .build();

          //envoa a requisição, ignora o corpo da respota, so importa o codigo
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding()); 

            //se retornar 200, devolve true (sucesso)
            return response.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

