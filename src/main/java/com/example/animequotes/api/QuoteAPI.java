package com.example.animequotes.api;

import com.example.animequotes.entities.Quote;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class QuoteAPI {
    private static final String RANDOM_QUOTE_URL = "https://animechan.vercel.app/api/random";

    public static Quote getRandomQuote(String language) {
        return getRandomTranslatedQuote(new Quote(language));
    }

    private static Quote getRandomTranslatedQuote(Quote quote) {
        try {
            URL url = new URL(RANDOM_QUOTE_URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
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

                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                quote.setQuote((String) data_obj.get("quote"));
                quote.setAnime((String) data_obj.get("anime"));
                quote.setCharacter((String) data_obj.get("character"));

                return quote;
            }

        } catch (IOException | ParseException e) {

            quote.setQuote("Site is current unavailable, we work on this");
            quote.setCharacter("Vladz");
            quote.setAnime("Real life");

            return quote;
        }

    }
}
