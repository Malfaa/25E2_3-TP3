package org.example.Exercicio12;

import java.net.HttpURLConnection;

import static org.example.HttpResponseUtil.getConnection;
import static org.example.HttpResponseUtil.respostaRequisicao;

public class Exercicio12 {
    public static int responseCode;
    static StringBuilder resposta;
    public static void main (String[] args){
        try{
            getAll();
            getISBN();
            post();
            put();
            delete(resposta.toString());

        }catch(Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    public static void getAll() { //OK
        try{
            HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/simpleapi/items","GET");

            System.out.println(respostaRequisicao(connection));
            System.out.println(connection.getResponseCode());

            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no GETALL: " + e.getMessage());
        }
    }

    public static void getISBN() {
        try{
            HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/simpleapi/randomisbn", "GET");
            StringBuilder jsonResponse = respostaRequisicao(connection);
            System.out.println("Resposta JSON do ISBN: " + jsonResponse);

            resposta = new StringBuilder(jsonResponse);

            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no GET_ISBN: " + e.getMessage());
        }
    }

    public static void post() {
        try{
            String jsonInputField = String.format("{ \"type\": \"book\",\"isbn13\": \"%s\",\"price\": 5.99,\"numberinstock\": 5}", resposta);

            HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/simpleapi/items","POST");

            System.out.println(respostaRequisicao(connection, jsonInputField));
            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no POST: " + e.getMessage());
        }
    }

    public static void put() {
        try{
            HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/simpleapi/items","PUT");
            String jsonInputField = String.format("{ \"type\": \"Livro diferente\",\"isbn13\": \"%s\",\"price\": 5.99,\"numberinstock\": 50}", resposta);

            System.out.println(respostaRequisicao(connection, jsonInputField));
            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no PUT: " + e.getMessage());
        }
    }

    public static void delete(String isbn) {
        try{
            HttpURLConnection connection = getConnection(String.format("https://apichallenges.eviltester.com/simpleapi/items/%s", resposta),"DELETE");
            responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            System.out.println(respostaRequisicao(connection));
            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no DELETE: " + e.getMessage());
        }
    }

}