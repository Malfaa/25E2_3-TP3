package org.example.Exercicio7;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.*;

public class Exercicio7 {

    public static void main(String[] args) {
        try {
            atualizarEntidadePost();
        }catch (Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }


    private static void atualizarEntidadePost() throws URISyntaxException, IOException {
        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities/10", "POST");

        String jsonInputString = "{\"name\": \"atualizado\"}";
        System.out.println("Resposta JSON: " + respostaRequisicao(connection, jsonInputString));
        System.out.println("Código resposta: " + connection.getResponseCode());
        System.out.println("Método usado: " + connection.getRequestMethod());


        connection.disconnect();
    }
}
