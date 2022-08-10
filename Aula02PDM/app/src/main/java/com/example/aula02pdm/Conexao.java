package com.example.aula02pdm;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {

    public InputStream obterRespostaHttp(String end){

        try {

            URL url = new URL(end);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return new BufferedInputStream(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;

    }

}
