package com.example.animequotes.entities;

import com.example.animequotes.api.Translator;

public class Quote {
    private String quote;
    private String anime;
    private String character;
    private String language = "en";

    public Quote() {
    }

    public Quote(String language) {
        this.language = language;
    }

    public Quote(String quote, String anime, String character) {
        this.quote = quote;
        this.anime = anime;
        this.character = character;
    }

    public Quote(String quote, String anime, String character, String language) {
        this.anime = anime;
        this.character = character;
        this.language = language;

        this.quote = Translator.translate(quote, language);
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        if (language.equals("en"))
            this.quote = quote;
        else
            this.quote = Translator.translate(quote, language);
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;

        if (quote != null)
            this.quote = Translator.translate(quote, language);
    }
}
