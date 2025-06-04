package org.example.Exercicio11;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.getConnection;

public class Exercicio11{
    public static void main (String[] args){
        try{
            verificaMetodos();
        }catch(Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    public static void verificaMetodos() throws IOException, URISyntaxException {

        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities", "OPTIONS");

        int responseCode = connection.getResponseCode();
        System.out.println("Código de resposta: " + responseCode);

        String metodosPermitidos = connection.getHeaderField("Allow");
        if(metodosPermitidos != null){
            System.out.println("Métodos permitidos: " + metodosPermitidos);
        }

        connection.disconnect();
    }
}