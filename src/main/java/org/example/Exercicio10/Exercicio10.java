package org.example.Exercicio10;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.getConnection;
import static org.example.HttpResponseUtil.respostaRequisicao;

public class Exercicio10 {
    public static void main (String[] args){
        try{
            deleteInvalido();
        }catch(Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    public static void deleteInvalido() throws IOException, URISyntaxException {

        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities/2", "DELETE");

        System.out.println(connection.getResponseCode());

        System.out.println(respostaRequisicao(connection));

    }
}