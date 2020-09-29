package com.myblog.demo.repository;

import com.myblog.demo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository1 {
    @Autowired
    ArticleRepository articleRepository;


    public Page<Article> findAllByAuthor(String loginUser, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, 10, sort);
        return articleRepository.findAllByAuthor(loginUser, pageable);
    }

    public Article getOne(Integer id) {
        return articleRepository.getOne(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void deleteById(Integer id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> findAllByAuthorAndArticleTitleLike(String loginUser, String s, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, 10, sort);
        //传入的参数中 user表示作者、"%"+str+"%"表示模糊搜索含str内容的文章标题
        return articleRepository.findAllByAuthorAndArticleTitleLike(loginUser, "%"+s+"%", pageable);
    }

    public Page<Article> findAll(Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, 10, sort);
        return articleRepository.findAll(pageable);
    }

    public Page<Article> findAllByArticleTitleLike(String s, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, 10, sort);
        return articleRepository.findAllByArticleTitleLike("%"+s+"%", pageable);

    }
}
