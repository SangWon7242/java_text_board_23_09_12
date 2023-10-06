package com.sbs.exam.board.container;

import com.sbs.exam.board.repository.ArticleRepository;
import com.sbs.exam.board.service.ArticleService;
import com.sbs.exam.board.session.Session;
import com.sbs.exam.board.controller.UsrArticleController;
import com.sbs.exam.board.controller.UsrMemberController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static ArticleRepository articleRepository;
  public static ArticleService articleService;

  public static UsrArticleController usrArticleController;
  public static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();

    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();
  }

  public static Scanner getSc() {
    return sc;
  }

  public static ArticleRepository getArticleRepository() {
    return articleRepository;
  }

  public static ArticleService getArticleService() {
    return articleService;
  }

  public static UsrArticleController getUsrArticleController() {
    return usrArticleController;
  }

  public static UsrMemberController getUsrMemberController() {
    return usrMemberController;
  }

  public static Session getSession() {
    return session;
  }
}
