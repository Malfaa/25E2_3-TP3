package org.example.Exercicio9;

import org.example.HttpResponseUtil;

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

        private static void verificarEntidade(String urlParaGet) throws URISyntaxException, IOException {
        HttpURLConnection getConnection = null;
        try {
            getConnection = HttpResponseUtil.getConnection(urlParaGet, "GET");
            int responseCode = getConnection.getResponseCode();
            System.out.println("Código Resposta GET: " + responseCode);
            System.out.println("Resposta JSON GET: " + HttpResponseUtil.respostaRequisicao(getConnection));
        } finally {
            if (getConnection != null) {
                getConnection.disconnect();
            }
        }
    }

}