package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    private String id;
    private String title;
    private String content; // HTML content from Markdown
    private LocalDate date;
    private String summary;
}
