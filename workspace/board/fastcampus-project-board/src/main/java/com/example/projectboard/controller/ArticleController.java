package com.example.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * /articles  - done
 * /articles/{article-id}
 * /articles/search
 * /articles/search-hashtag
 * */
@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles (ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }
}
