package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

// not asynchronous, will block your main thread.
public class MethodOne {

    private static HttpURLConnection connection;

    MethodOne(){
        // Method 1: java.net.HttpUTLConnection, for pre java 11.
        // response will be an input stream. In order to read an input stream we will use buffer reader
        BufferedReader reader;
        String line;

        // StringBuffer is like String but has more functionality.
        // StringBuffer represents growable and writable character sequences. ay have characters and substrings inserted
        // in the middle or appended to the end. It will automatically grow to make room for such additions and often
        // has more characters preallocate than are actually needed, to allow room for growth.
        // Simply put, this class has a lot methods for string manipulation. Length, capacity, insert, reverse, etc.
        StringBuilder responseContent = new StringBuilder();

        try{
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();

            // Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            // System.out.println(status); I got response 200 (success)

            // wrapping bufferReader with operations such as FileReaders and InputStreamReaders will buffer the input from
            // the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read
            // from the file, converted into characters, and then returned, which can be very inefficient.
            if (status >299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                // Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'),
                // a carriage return ('\r'), a carriage return followed immediately by a line feed, or by reaching
                // the end-of-file (EOF).
            } else  {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            }
            while((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
            //System.out.println(responseContent.toString());

            /* static method rules:
               1. can only call other static methods
               2. can only access static variables
               3. cannot use this or super keyword
             */
            MethodTwo.parse(responseContent.toString());
            //java.net.MalformedURLException is subclass/extends of IoException so we can remove the extra
            // catch statement.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}
