package com.i012114.asynctaskinternet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by aula7 on 19/09/17.
 */

public class HttpManager {

    public static String getData(String uri) throws IOException {
        BufferedReader reader = null;


        //  Clase url de japa para manejar rutas (dar formato)
        URL url = new URL(uri);

        // Clase que me permite hacer la conexion a internet
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Declarar variable para abrir un buffer de recoleccion de datos
        StringBuilder stringBuilder = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

        String line;

        while ((line = reader.readLine()) != null){
            stringBuilder.append(line + "\n");

        }

        return stringBuilder.toString();
    }
}
