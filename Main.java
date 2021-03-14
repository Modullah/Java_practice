package com.company;

import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;



public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) {
	// Method 1: java.net.HttpUTLConnection
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        //reponse will be an input stream. In order to read an input stream we will use buffer reader

        try{
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();

            //Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            //System.out.println(status); I got response 200 (success)


            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
