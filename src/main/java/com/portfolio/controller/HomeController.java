package com.portfolio.controller;

import com.portfolio.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuoteService quoteService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("quote", quoteService.getRandomQuote());
        model.addAttribute("activePage", "home");
        return "index";
    }
}
