package com.sbs.exam.board.service;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.repository.ArticleRepository;
import com.sbs.exam.board.vo.Article;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.getArticleRepository();
  }

  public void makeTestDate() {
    for(int i = 1; i <= 100; i++ ) {
      String title = "제목" + i;
      String content = "내용" + i;
      write(1, 1, title, content);
    }
  }

  public List<Article> getArticles(String searchKeyword, String orderBy) {
    return articleRepository.getArticles(searchKeyword, orderBy);
  }

  public int write(int boardId, int memberId, String title, String content) {
    return articleRepository.write(boardId, memberId, title, content);
  }

  public Article getArticleById(int id) {
    return articleRepository.getArticleById(id);
  }

  public void remove(Article article) {
    articleRepository.remove(article);
  }
}
