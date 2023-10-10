package com.sbs.exam.board.controller;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.service.ArticleService;
import com.sbs.exam.board.service.BoardService;
import com.sbs.exam.board.vo.Article;
import com.sbs.exam.board.vo.Board;
import com.sbs.exam.board.vo.Rq;

import java.util.List;

public class UsrArticleController {
  private ArticleService articleService;
  private BoardService boardService;

  public UsrArticleController() {
    articleService = Container.getArticleService();
    boardService = Container.getBoardService();

    articleService.makeTestDate();
  }

  public void actionWrite(Rq rq) {
    int boardId = rq.getIntParam("boardId", 0);

    if(boardId == 0) {
      System.out.println("boardId를 입력해주세요");
      return;
    }

    Board board = boardService.getBoardById(boardId);

    if(board == null) {
      System.out.println("존재하지 않는 게시판 번호입니다.");
      return;
    }

    System.out.printf("== %s 게시판 작성 ==\n", board.getName());
    System.out.printf("제목 : ");
    String title = Container.getSc().nextLine();

    System.out.printf("내용 : ");
    String content = Container.getSc().nextLine();

    int loginedMemberId = rq.getLoginedMemberId();
    int id = articleService.write(1, loginedMemberId, title, content);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc");

    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    List<Article> articles = articleService.getArticles(searchKeyword, orderBy);

    articles.stream()
        .forEach(article -> System.out.printf("%d / %s\n", article.getId(), article.getTitle()));
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("게시판 : %d\n", article.getBoardId());
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getContent());
    System.out.printf("작성자 : %d\n", article.getMemberId());
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    article.setTitle(Container.getSc().nextLine());
    System.out.printf("새 내용 : ");
    article.setContent(Container.getSc().nextLine());

    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    articleService.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
