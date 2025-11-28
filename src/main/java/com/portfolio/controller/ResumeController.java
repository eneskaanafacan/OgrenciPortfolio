package com.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    @GetMapping("/resume")
    public String resume(Model model) {
        model.addAttribute("activePage", "resume");
        return "resume";
    }
}
