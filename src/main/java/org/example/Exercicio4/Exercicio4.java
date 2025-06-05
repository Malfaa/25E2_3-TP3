package org.example.Exercicio4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import static org.example.HttpResponseUtil.getConnection;
import static org.example.HttpResponseUtil.respostaRequisicao;

public class Exercicio4 {
    public static void main(String[] args){
        try {
            paramFic();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void paramFic() throws URISyntaxException, IOException {
        HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5", "GET");

        try{
            System.out.println(respostaRequisicao(connection));
            System.out.println(connection.getResponseCode());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally{
            connection.disconnect();
        }
    }
}