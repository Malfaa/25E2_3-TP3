package org.example.Exercicio8;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.getConnection;
import static org.example.HttpResponseUtil.respostaRequisicao;

public class Exercicio8 {
    public static void main (String[] args){
        try{
            atualizarEntidadePut();
        }catch(Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    private static void atualizarEntidadePut() throws IOException, URISyntaxException {
        String jsonInputString = "{\"name\": \"atualizado\"}";

        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities/10", "PUT");

        System.out.println(respostaRequisicao(connection, jsonInputString));
        System.out.println("Código de resposta: " + connection.getResponseCode());
        System.out.println("Método usado: " + connection.getRequestMethod());
        connection.disconnect();
    }


}