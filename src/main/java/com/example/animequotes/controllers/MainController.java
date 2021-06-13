package com.example.animequotes.controllers;

import com.example.animequotes.api.QuoteAPI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String getQuote(Model model, @RequestParam(name = "theme", defaultValue = "light", required = false) String theme) {
        model.addAttribute("quote", QuoteAPI.getRandomQuote("en"));

        return "index";
    }

    @GetMapping("/{lang}")
    public String getQuote(Model model, @PathVariable String lang, @RequestParam(name = "theme", defaultValue = "light", required = false) String theme) {
        model.addAttribute("quote", QuoteAPI.getRandomQuote(lang));

        return "index";
    }


}
