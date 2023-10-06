package com.sbs.exam.board.repository;

import com.sbs.exam.board.vo.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastId;
  private List<Article> articles;

  public ArticleRepository() {
    lastId = 0;
    articles = new ArrayList<>();
  }

  public int write(String title, String content) {
    int id = lastId + 1;
    Article article = new Article(id, title, content);
    articles.add(article);
    lastId = id;

    return id;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public Article getArticleById(int id) {
    for(Article article : articles) {
      if(article.getId() == id) {
        return article;
      }
    }

    return null;
  }

  public void remove(Article article) {
    articles.remove(article);
  }
}
