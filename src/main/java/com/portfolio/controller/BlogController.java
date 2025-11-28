package com.portfolio.controller;

import com.portfolio.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("posts", blogService.getAllPosts());
        model.addAttribute("activePage", "blog");
        return "blog";
    }
}
