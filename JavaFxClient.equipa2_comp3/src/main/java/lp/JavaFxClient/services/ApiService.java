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
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .GET()
                    .build();
            
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            
            if (response.statusCode() >= 400) {
                return "ERROR (" + response.statusCode() + "): " + response.body();
            }

            return response.body();

        } catch (Exception e) {
            e.printStackTrace(); 
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }

    public String post(String path, String json) {
        try {
            HttpRequest.BodyPublisher body =
                    (json == null || json.isEmpty())
                            ? HttpRequest.BodyPublishers.noBody()
                            : HttpRequest.BodyPublishers.ofString(json);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .header("Content-Type", "application/json")
                    .POST(body)
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                return "ERROR (" + response.statusCode() + "): " + response.body();
            }

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }

    
    public String put(String path, String json) {
        try {
            HttpRequest.BodyPublisher body =
                    (json == null || json.isEmpty())
                            ? HttpRequest.BodyPublishers.noBody()
                            : HttpRequest.BodyPublishers.ofString(json);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + path))
                    .header("Content-Type", "application/json")
                    .PUT(body)
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

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
                    .uri(URI.create(BASE_URL + path))
                    .DELETE()
                    .build();

            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return body == null ? "" : body;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getClass().getName() + " - " + e.getMessage();
        }

    }
}

