package org.example.Exercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Exercicio4 {
    public static void main(String[] args){
        try {
            paramFic();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void paramFic() throws URISyntaxException, IOException {
        BufferedReader reader;
        StringBuilder response = new StringBuilder();
        URL url = new URI("https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5").toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try{
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "applicaton/json");
            int responseCode = connection.getResponseCode();

            System.out.println("Código resposta: " + responseCode);

            if(responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE){
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while((inputLine = reader.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                while((inputLine = reader.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }
            }

            System.out.println("Resposta do Request: " + response);
            reader.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally{
            connection.disconnect();
        }
    }
}