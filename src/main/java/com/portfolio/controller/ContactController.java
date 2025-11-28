package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final FirebaseService firebaseService;
    private final com.portfolio.service.EmailService emailService;

    // Simple in-memory rate limiting: IP -> Last Request Time
    private final java.util.Map<String, Long> rateLimitMap = new java.util.concurrent.ConcurrentHashMap<>();

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        model.addAttribute("activePage", "contact");
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute ContactMessage contactMessage,
            jakarta.servlet.http.HttpServletRequest request,
            Model model) {

        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        if (rateLimitMap.containsKey(clientIp)) {
            long lastRequestTime = rateLimitMap.get(clientIp);
            if (currentTime - lastRequestTime < 60000) { // 1 minute
                model.addAttribute("error", "Lütfen tekrar mesaj göndermeden önce bekleyin.");
                model.addAttribute("activePage", "contact");
                return "contact";
            }
        }

        rateLimitMap.put(clientIp, currentTime);

        firebaseService.saveMessage(contactMessage);
        emailService.sendEmail(contactMessage);

        model.addAttribute("success", true);
        model.addAttribute("activePage", "contact");
        return "contact";
    }
}
