package org.example.Exercicio5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercicio5 {
    public static void main(String[] args){
        try{
            novaEntidade();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void novaEntidade() throws URISyntaxException, IOException {
        String jsonInputString = "{\"name\": \"aluno\"}";
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities").toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try{
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                dos.write(input);
            }

            var responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            System.out.println("Código Resposta: " + response);

            if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            } else {
                if (connection.getErrorStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                } else {
                    System.out.println("Nenhum corpo de resposta ou erro disponível para o código de status: " + responseCode);
                }
            }

            if (reader != null) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine.trim());
                }
            }

            System.out.println("Resposta: " + response);
            assert reader != null;
            reader.close();

        }catch(Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }finally{
            connection.disconnect();
        }
    }
}