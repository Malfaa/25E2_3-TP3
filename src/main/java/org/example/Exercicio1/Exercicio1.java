package org.example.Exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Exercicio1 {
    public static void main(String[] args) {
        try{
            conexao();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void conexao() throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities").toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            var responseCode = connection.getResponseCode();
            System.out.println("Reposta código: " + responseCode);

            BufferedReader reader;
            if(100 <= responseCode && responseCode <=399) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            while((inputLine = reader.readLine()) != null){
                response.append(inputLine).append('\n');
            }

            System.out.println(response);

            reader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.disconnect();
        }
    }
}