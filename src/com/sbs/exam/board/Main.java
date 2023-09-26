package com.sbs.exam.board;

import java.util.*;

public class Main {

  static void makeTestDate(List<Article> articles) {
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;
    List<Article> articles = new ArrayList<>();

    makeTestDate(articles);

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
        System.out.println("== 게시물 등록 ==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String content = sc.nextLine();

        int id = articleLastId + 1;
        articleLastId = id;

        Article article = new Article(id, title, content);
        
        articles.add(article); // list에 게시물 추가

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
      }
      else if(rq.getUrlPath().equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
        System.out.println("-------------------");
        System.out.println("번호 / 제목");
        System.out.println("-------------------");

        // articles에는 현재 정렬이 안된, 즉 1 ~ 3순으로 들어있는 리모콘의 복사본이 들어있다.
        // 그 리모콘의 복사본을 sortedArticles한테 연결시켜 줌.
        List<Article> sortedArticles = articles;
        boolean orderByIdDesc = true;

        if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
          orderByIdDesc = false;
        }

        if(orderByIdDesc) {
          sortedArticles = Util.reverseList(sortedArticles);
        }

        sortedArticles.stream()
            .forEach(article -> System.out.printf("%d / %s\n", article.id, article.title));


      }
      else if(rq.getUrlPath().equals("/usr/article/detail")) {
        if(params.containsKey("id") == false) {
          System.out.println("id를 입력해주세요.");
          continue;
        }

        int id = 0;

        try {
          id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
          System.out.println("id를 정수 형태로 입력해주세요.");
          continue;
        }

        // 게시물이 아예 없는 경우
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        // 내가 입력한 id가 현재 게시물에 수량을 초과한 경우
        if(id > articles.size()) {
          System.out.println("해당 게시물은 존재하지 않습니다.");
          continue;
        }

        Article article = articles.get(id - 1);

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);
      }
      else if(rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    sc.close();
  }
}

class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return String.format("{id : %d, title : \"%s\", content : \"%s\"}", id, title, content);
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getUrlPathFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) {
      return params;
    }

    String queryStr = urlBits[1];

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]); // key, value
    }

    return params;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만듭니다. 즉 정렬이 반대인 복사본리스트를 만들어서 반환합니다.
  public static<T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for ( int i = list.size() - 1; i >= 0; i-- ) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}