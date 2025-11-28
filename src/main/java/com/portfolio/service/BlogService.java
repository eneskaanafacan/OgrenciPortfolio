package com.portfolio.service;

import com.portfolio.model.BlogPost;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

        public List<BlogPost> getAllPosts() {
                List<BlogPost> posts = new ArrayList<>();
                PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

                try {
                        Resource[] resources = resolver.getResources("classpath:posts/*.md");
                        for (Resource resource : resources) {
                                posts.add(parsePost(resource));
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return posts;
        }

        private BlogPost parsePost(Resource resource) throws IOException {
                BufferedReader reader = new BufferedReader(
                                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                List<String> lines = reader.lines().collect(Collectors.toList());

                String title = "Untitled";
                LocalDate date = LocalDate.now();
                String summary = "";
                StringBuilder content = new StringBuilder();

                boolean inFrontMatter = false;
                for (int i = 0; i < lines.size(); i++) {
                        String line = lines.get(i);
                        if (line.trim().equals("---")) {
                                if (i == 0) {
                                        inFrontMatter = true;
                                        continue;
                                } else if (inFrontMatter) {
                                        inFrontMatter = false;
                                        continue;
                                }
                        }

                        if (inFrontMatter) {
                                if (line.startsWith("title:"))
                                        title = line.substring(6).trim();
                                else if (line.startsWith("date:"))
                                        date = LocalDate.parse(line.substring(5).trim());
                                else if (line.startsWith("summary:"))
                                        summary = line.substring(8).trim();
                        } else {
                                content.append(line).append("\n");
                        }
                }

                Parser parser = Parser.builder().build();
                Node document = parser.parse(content.toString());
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                String htmlContent = renderer.render(document);

                return new BlogPost(resource.getFilename(), title, htmlContent, date, summary);
        }
}
