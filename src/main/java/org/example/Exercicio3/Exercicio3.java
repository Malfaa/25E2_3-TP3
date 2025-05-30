package org.example.Exercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Exercicio3 {
    public static void main(String[] args) {
        try {
            falhaConexao();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void falhaConexao() throws URISyntaxException, IOException {
        BufferedReader reader ;
        StringBuilder response = new StringBuilder();
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities/13").toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            var responseCode = connection.getResponseCode();
            System.out.println("Código resposta: " + responseCode);

            reader = (responseCode > 99 && responseCode < 400) ?
                    new BufferedReader(new InputStreamReader(connection.getInputStream())) :
                    new BufferedReader(new InputStreamReader(connection.getErrorStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine).append('\n');
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error mensagem => "+ e.getMessage());
        } finally {
            connection.disconnect();
        }
    }
}