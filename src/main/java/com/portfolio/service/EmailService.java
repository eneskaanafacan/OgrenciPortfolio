package com.portfolio.service;

import com.portfolio.model.ContactMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(ContactMessage message) {
        System.out.println("Mail gönderiliyor: kaan.afacan@bil.omu.edu.tr");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("noreply@portfolio.com");
            mailMessage.setTo("eneskaanafacan35@gmail.com");
            mailMessage.setSubject("Yeni İletişim Mesajı: " + message.getName());
            mailMessage.setText("Gönderen: " + message.getName() + " (" + message.getEmail() + ")\n\n" +
                    "Mesaj:\n" + message.getMessage());

            mailSender.send(mailMessage);
            System.out.println("Email sent successfully to kaan.afacan@bil.omu.edu.tr");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
