package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
  int articleLastId;
  List<Article> articles;

  App() {
    articleLastId = 0;
    articles = new ArrayList<>();
  }

  void makeTestDate() {
    // 테스트 게시물을 100개로 늘림.
    for(int i = 1; i <= 100; i++ ) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public void run() {
    Scanner sc = Container.sc;

    makeTestDate();

    if(articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      Map<String, String> params = rq.getParams();

      if(rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite();
      }
      else if(rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq);
      }
      else if(rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    sc.close();
  }

  private void actionUsrArticleDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    articles.remove(foundArticle);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  private void actionUsrArticleModify(Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    foundArticle.title = Container.sc.nextLine();
    System.out.printf("새 내용 : ");
    foundArticle.content = Container.sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", foundArticle.id);
  }

  private void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    // 게시물이 아예 없는 경우
    // 내가 입력한 id가 현재 게시물에 수량을 초과한 경우
    if(articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
  }

  private void actionUsrArticleWrite() {
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

  private void actionUsrArticleList(Rq rq) {
    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    Map<String, String> params = rq.getParams();

    // 검색 시작
    List<Article> filteredArticles = articles;

    if(params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

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
    boolean orderByIdDesc = true;

    if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if(orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    sortedArticles.stream()
        .forEach(article -> System.out.printf("%d / %s\n", article.id, article.title));
    // 정렬 로직 끝
  }
}
