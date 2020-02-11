package com.example.consumindojsonsemrotulo1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Auxiliar {
    private static final String TAG = Auxiliar.class.getSimpleName();

    public String conectar(String url) {

        String response = null;

        try {
            URL endereco = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) endereco.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            response = converterStreamEmString(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String converterStreamEmString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String linha;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append("\n");
            }//while
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }

}
