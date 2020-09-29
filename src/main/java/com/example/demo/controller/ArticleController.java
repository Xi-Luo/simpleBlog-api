package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public List<Article> getAllArticle() throws Exception{
        return articleService.getAll();
    }

    @CrossOrigin
    @GetMapping("/view")
    @ResponseBody
    public Article getOne(@RequestParam("id") Integer id) throws Exception{
        return articleService.getOne(id);
    }

    @CrossOrigin
    @PostMapping("/post")
    @ResponseBody
    public Boolean postIt(@RequestBody Article article){
        if(articleService.add(article)){
            return true;
        }
        return false;
    }

    @CrossOrigin
    @GetMapping("/userArticle")
    @ResponseBody
    public List<Article> userArticle(@RequestParam("username") String username){
        return articleService.userArticle(username);
    }

    @CrossOrigin
    @PostMapping("/delete")
    @ResponseBody
    public void deleteId(@RequestBody Map<String,Object> param){
        articleService.deleteId(Integer.parseInt(param.get("id").toString()));
    }
}
