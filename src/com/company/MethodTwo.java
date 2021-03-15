package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MethodTwo {
    // Method 2: java.net.http.HttpClient , this method will handle async for you. Using java 11 api.

    MethodTwo() {

        HttpClient  client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
        // completetableFuture implements future class, library for multithreading in java.
        // using lambda expressions to use the available functions in the CompleteableFuture class
        // thenApply(): Takes function and apply it on to the result of the previous stage. Remember that thenApply()
        // is a synchronous mapping function. It returns a CompletionStage holding the result of the function.


        //The double colon (::) operator, also known as method reference operator in Java, is used to call a method by
        // referring to it with the help of its class directly. They behave exactly as the lambda expressions. The only
        // difference it has from lambda expressions is that this uses direct reference to the method by name instead of
        // providing a delegate to the method.
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        // thenApply(): Takes function and apply it on to the result of the previous stage. Remember that thenApply() is a
        // synchronous mapping function. It returns a CompletionStage holding the result of the function.
        // thenAccept(): Takes a consumer and returns CompletionStage<Void>. thenAccept() is usually called during the
        // final stages of pipeline and returns a final value.
        // The java.lang.string.join() method concatenates the given elements with the delimiter and returns the
        // concatenated string.Note that if an element is null, then null is added.The join() method is included in java string since JDK 1.8.
    }

    public static String parse(String responseBody) {

        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id + " " + title + " " + userId);
        }
        return null;
    }
}
