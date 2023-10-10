package com.sbs.exam.board.repository;

import com.sbs.exam.board.util.Util;
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

  public List<Article> getArticles(String searchKeyword, String orderBy) {

    // articles 1 ~ 100까지의 게시물 리스트 들어있다.
    List<Article> filteredArticles = articles;

    if(searchKeyword.length() > 0) {
      filteredArticles = new ArrayList<>();

      for(Article article : articles) {
        boolean matched = article.getTitle().contains(searchKeyword) || article.getContent().contains(searchKeyword);

        if(matched) {
          filteredArticles.add(article);
        }
      }
    }

    List<Article> sortedArticles = filteredArticles;

    boolean orderByIdDesc = orderBy.equals("idDesc");

    if(orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    return sortedArticles;
  }

  public List<Article> getOriginArticles() {
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
