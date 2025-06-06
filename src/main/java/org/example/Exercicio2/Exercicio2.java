package org.example.Exercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Exercicio2 {
    public static void main(String[] args) {
        try {
            conexao(8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void conexao(int id) throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URI(String.format("https://apichallenges.eviltester.com/sim/entities/%d", id)).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader;
            int  responseCode = connection.getResponseCode();

            if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("Código resposta: " + responseCode);

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            System.out.println(response);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.disconnect();
        }
    }
}