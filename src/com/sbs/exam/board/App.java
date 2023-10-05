package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.Scanner;

public class App {
  public void run() {
    Scanner sc = Container.sc;

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if(rq.getUrlPath().equals("/usr/article/write")) {
        Container.usrArticleController.actionWrite(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/list")) {
        Container.usrArticleController.showList(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/detail")) {
        Container.usrArticleController.showDetail(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")) {
        Container.usrArticleController.actionModify(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")) {
        Container.usrArticleController.actionDelete(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/join")) {
        Container.usrMemberController.actionJoin(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin(rq);
      }
      else if(rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    sc.close();
  }
}
