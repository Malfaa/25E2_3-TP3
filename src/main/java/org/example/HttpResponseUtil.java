package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class HttpResponseUtil{

    private HttpResponseUtil(){}

    public static boolean isSucesso(int responseCode){
        return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
    }

    public static HttpURLConnection getConnection(String urlStr, String requestMethod) throws URISyntaxException, IOException {
        URL url = new URI(urlStr).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("Accept", "application/json");

        if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
        }else if(requestMethod.equals("DELETE")) {
            conn.setDoOutput(true);
        }

        return conn;
    }
    public static StringBuilder respostaRequisicao(HttpURLConnection connection) throws IOException{
        BufferedReader reader;
        String input;
        StringBuilder response = new StringBuilder();

        if(HttpResponseUtil.isSucesso(connection.getResponseCode())){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        while((input = reader.readLine()) != null){
            response.append(input);
        }
        reader.close();
        return response;
    }

    public static StringBuilder respostaRequisicao(HttpURLConnection connection, String json) throws IOException{
        BufferedReader reader;
        String input;
        StringBuilder response = new StringBuilder();

        if(connection.getRequestMethod().equals("POST") || connection.getRequestMethod().equals("PUT")){
            gravarJson(connection, json);
        }

        if(HttpResponseUtil.isSucesso(connection.getResponseCode())){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        while((input = reader.readLine()) != null){
            response.append(input);
        }
        reader.close();
        return response;
    }

    private static void gravarJson(HttpURLConnection connection, String jsonInputString) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            dos.write(input);
        }
    }

    public static int regexId(StringBuilder str){
        String regex = "\"id\":\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        String idValor = "";

        if (matcher.find()) {
            idValor = matcher.group(1);
            System.out.println(idValor);
        } else {
            System.out.println("ID não encontrado.");
        }
        return Integer.parseInt(idValor);
    }
}