package org.example.Exercicio9;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.*;

public class Exercicio9 {
    public static void main (String[] args){
        try{
            entidadeValida();
        }catch(Exception e){
            System.out.println("Mensagem de error: "+ e.getMessage());
        }
    }

    public static void entidadeValida() throws IOException, URISyntaxException {
        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities/9", "DELETE");

        int responseCode = connection.getResponseCode();
        System.out.println("Código de resposta: " + responseCode);

        System.out.println(respostaRequisicao(connection));
        verificarEntidade("https://apichallenges.eviltester.com/sim/entities/9");
        connection.disconnect();
    }

}