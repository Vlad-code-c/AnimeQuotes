package com.example.animequotes.api;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Translator {
    public static String translate(String text, String language) {

        try {
            String encoded_text = URLEncoder.encode(text, StandardCharsets.UTF_8.name());


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://translated-mymemory---translation-memory.p.rapidapi.com/api/get?q=" + encoded_text + "&langpair=en%7C" + language + "&de=a%40b.c&onlyprivate=0&mt=1"))
                    .header("x-rapidapi-key", "2816248047msh653b0889e6dee7cp13dc20jsnf36c32477aa7")
                    .header("x-rapidapi-host", "translated-mymemory---translation-memory.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = null;


            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return deserializeJson(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "INVALID TARGET";
    }


    private static String deserializeJson(String json) {
        try {

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(json);

            JSONObject trd_obj = (JSONObject) data_obj.get("responseData");
            return (String) trd_obj.get("translatedText");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "INVALID TARGET";
    }
}
