package com.portfolio.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    private final List<String> quotes = Arrays.asList(
            "Bütün ümidim gençliktedir.",
            "Ey yükselen yeni nesil, istikbal sizindir. Cumhuriyet’i biz kurduk, O’nu yükseltecek ve sürdürecek sizlersiniz.",
            "Ey Türk Gençliği! Birinci vazifen, Türk istiklâlini, Türk Cumhuriyetini, ilelebet, muhafaza ve müdafaa etmektir.",
            "Sizler, yani yeni Türkiye’nin genç evlatları! Yorulsanız dahi beni takip edeceksiniz… Dinlenmemek üzere yürümeye karar verenler, asla ve asla yorulmazlar.",
            "Türk çocuğu ecdadını tanıdıkça daha büyük işler yapmak için kendinde kuvvet bulacaktır.",
            "Eğer bugün batı teknikte bir üstünlük gösteriyorsa, ey Türk Çocuğu, o kabahat da senin değil, senden öncekilerin affedilmez ihmalinin bir sonucudur. Şunu da söyleyeyim ki, çok zekisin! .. Bu belli. Fakat zekânı unut! .. Daima çalışkan ol…",
            "Gençler cesaretimizi takviye ve idame eden sizlersiniz. Siz, almakta olduğunuz terbiye ve irfan ile insanlık ve medeniyetin, vatan sevgisinin, fikir hürriyetinin en kıymetli timsali olacaksınız.");

    public String getRandomQuote() {
        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
