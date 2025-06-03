package org.example.Exercicio7;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercicio7 {

    public static void main(String[] args) {
        try {
            atualizarEntidade();
        }catch (Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    private static void atualizarEntidade() throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader reader;
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities/10").toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String jsonInputString = "{\"name\": \"atualizado\"}";

        try {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                dos.write(input);
            }

            var responseCode = connection.getResponseCode();
            System.out.println("Código Resposta: " + responseCode);

            if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while((inputLine = reader.readLine()) != null){
                    response.append(inputLine).append("\n");
                }
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            System.out.println("Resposta JSON: " + response);
            reader.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            connection.disconnect();
        }
    }
}
