package org.example.Exercicio12;

import java.net.HttpURLConnection;

import static org.example.HttpResponseUtil.*;

public class Exercicio12 {
    static StringBuilder resposta;
    static int id;
    static StringBuilder isbn;
    public static void main (String[] args){
        try{
            getAll();
            getISBN();
            post();
            put();
            delete();

        }catch(Exception e){
            System.out.println("Mensagem de error: " + e.getMessage());
        }
    }

    public static void getAll() {
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
            System.out.println("Resposta ISBN: " + jsonResponse);

            isbn = new StringBuilder(jsonResponse);

            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no GET_ISBN: " + e.getMessage());
        }
    }

    public static void post() {
        try{
            String jsonInputField = String.format("{ \"type\": \"book\",\"isbn13\": \"%s\",\"price\": 5.99,\"numberinstock\": 5}", isbn);

            HttpURLConnection connection = getConnection("https://apichallenges.eviltester.com/simpleapi/items","POST");

            resposta = respostaRequisicao(connection, jsonInputField);
            id = regexId(resposta);
            System.out.println(resposta);

            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no POST: " + e.getMessage());
        }
    }

    public static void put() {
        try{
            String jsonInputField = String.format("{ \"id\": %d,\"type\": \"blu-ray\",\"isbn13\": \"%s\",\"price\": 30.33,\"numberinstock\": 12}",id,  isbn);

            HttpURLConnection connection = getConnection(String.format("https://apichallenges.eviltester.com/simpleapi/items/%d", id),"PUT");

            System.out.println(respostaRequisicao(connection, jsonInputField));
            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no PUT: " + e.getMessage());
        }
    }

    public static void delete() {
        try{
            HttpURLConnection connection = getConnection(String.format("https://apichallenges.eviltester.com/simpleapi/items/%d", id),"DELETE");

            respostaRequisicao(connection);
            System.out.println("Item removido com sucesso!");
            System.out.println(connection.getResponseCode());

            connection.disconnect();
        }catch(Exception e){
            System.out.println("Erro no DELETE: " + e.getMessage());
        }
    }

}