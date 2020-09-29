package com.example.demo.service;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public void deleteId(Integer id){
        articleDAO.deleteById(id);
    }

    public List<Article> userArticle(String username){
        return articleDAO.findAllByAuthor(username);
    }

    public List<Article> getAll(){
        var sort = Sort.by(Sort.Direction.DESC, "id");
        return articleDAO.findAll(sort);
    }

    public Article getOne(Integer id){
        return articleDAO.getOne(id);
    }

    public Boolean add(Article article){
        articleDAO.save(article);
        return true;
    }


}
