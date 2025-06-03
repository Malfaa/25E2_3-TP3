package org.example.Exercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Exercicio6 {
    public static void main(String[] args) {
        try{
            entRecemCriada();
        }catch (Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    private static void entRecemCriada() throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader reader;
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities/11").toURL();
        HttpURLConnection connection = (HttpURLConnection ) url.openConnection();
        try{
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");

            var responseCode = connection.getResponseCode();
            System.out.println("Código resposta: " + responseCode);

            if(responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE){
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while((inputLine = reader.readLine()) != null){
                    response.append(inputLine).append("\n");
                }
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            System.out.println("Resposta recebida: " + response);
            reader.close();
        }catch(Exception e){
            System.out.println("Mensagem de erro: " + e.getMessage());
        }finally{
            connection.disconnect();
        }
    }
}