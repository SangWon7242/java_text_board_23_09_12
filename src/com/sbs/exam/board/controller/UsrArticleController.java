package com.sbs.exam.board.controller;

import com.sbs.exam.board.Article;
import com.sbs.exam.board.Rq;
import com.sbs.exam.board.Util;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UsrArticleController {
  private int articleLastId;
  private List<Article> articles;

  public UsrArticleController() {
    articleLastId = 0;
    articles = new ArrayList<>();

    makeTestDate();

    if(articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }
  }

  public void makeTestDate() {
    // 테스트 게시물을 100개로 늘림.
    for(int i = 1; i <= 100; i++ ) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public void actionWrite(Rq rq) {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    System.out.printf("내용 : ");
    String content = Container.sc.nextLine();

    int id = articleLastId + 1;
    articleLastId = id;

    Article article = new Article(id, title, content);

    articles.add(article); // list에 게시물 추가

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    String searchKeyword = rq.getParam("searchKeyword", "");

    // 검색 시작
    List<Article> filteredArticles = articles;

    if(searchKeyword.length() > 0) {

      filteredArticles = new ArrayList<>();

      for(Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);

        if(matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝

    // 검색어가 없으면 filteredArticles는 articles랑 똑같다.
    List<Article> sortedArticles = filteredArticles;

    // 정렬 로직 시작
    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc");

    if(orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    sortedArticles.stream()
        .forEach(article -> System.out.printf("%d / %s\n", article.id, article.title));
    // 정렬 로직 끝
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    // 게시물이 아예 없는 경우
    // 내가 입력한 id가 현재 게시물에 수량을 초과한 경우
    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.content);
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    article.title = Container.sc.nextLine();
    System.out.printf("새 내용 : ");
    article.content = Container.sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    articles.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  private Article getArticleById(int id) {
    for(Article article : articles) {
      if(article.id == id) {
        return article;
      }
    }

    return null;
  }
}
