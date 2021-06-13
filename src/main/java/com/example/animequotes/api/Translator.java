package com.example.animequotes.api;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Translator {

    public static String translate(String text, String language) {
        String encoded_text = null;
        try {
            encoded_text = URLEncoder.encode(text, StandardCharsets.UTF_8.name());
            URL url = URI.create("https://translated-mymemory---translation-memory.p.rapidapi.com/api/get?q=" + encoded_text + "&langpair=en%7C" + language + "&de=a%40b.c&onlyprivate=0&mt=1").toURL();  //new URL("https://translated-mymemory---translation-memory.p.rapidapi.com/api/get?q=" + encoded_text + "&langpair=en%7C" + language + "&de=a%40b.c&onlyprivate=0&mt=1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-rapidapi-key", "2816248047msh653b0889e6dee7cp13dc20jsnf36c32477aa7");
            conn.setRequestProperty("x-rapidapi-host", "translated-mymemory---translation-memory.p.rapidapi.com");
            conn.connect();

            int responseCode = conn.getResponseCode();


            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                return deserializeJson(inline);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }



//    public static String translate(String text, String language) {
//
//        try {
//            String encoded_text = URLEncoder.encode(text, StandardCharsets.UTF_8.name());
//
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://translated-mymemory---translation-memory.p.rapidapi.com/api/get?q=" + encoded_text + "&langpair=en%7C" + language + "&de=a%40b.c&onlyprivate=0&mt=1"))
//                    .header("x-rapidapi-key", "2816248047msh653b0889e6dee7cp13dc20jsnf36c32477aa7")
//                    .header("x-rapidapi-host", "translated-mymemory---translation-memory.p.rapidapi.com")
//                    .method("GET", HttpRequest.BodyPublishers.noBody())
//                    .build();
//            HttpResponse<String> response = null;
//
//
//            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//            return deserializeJson(response.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "INVALID TARGET";
//    }


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
