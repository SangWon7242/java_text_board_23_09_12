package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.interceptor.Interceptor;
import com.sbs.exam.board.vo.Member;
import com.sbs.exam.board.vo.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private void forTestLoginByMemberId(int id) {
    Member member = Container.getMemberService().getMemberById(id);
    new Rq().login(member);
  }
  
  public void run() {
    Scanner sc = Container.getSc();

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("== 프로그램 시작 ==");
    
    // 프로그램 실행이 되자마자 회원 1번이 로그인 될 수 있게끔
    forTestLoginByMemberId(1);

    while (true) {
      Rq rq = new Rq();
      String promptName = "명령";

      if(rq.isLogined()) {
        Member loginedMember = rq.getLoginedMember();
        promptName = loginedMember.getLoginId();
      }

      System.out.printf("%s) ", promptName);
      String cmd = sc.nextLine();

      rq.setCommand(cmd);

      if(runInterceptor(rq) == false) {
        continue;
      }

      if(rq.getUrlPath().equals("/usr/article/write")) {
        Container.getUsrArticleController().actionWrite(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/list")) {
        Container.getUsrArticleController().showList(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/detail")) {
        Container.getUsrArticleController().showDetail(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")) {
        Container.getUsrArticleController().actionModify(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")) {
        Container.getUsrArticleController().actionDelete(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/join")) {
        Container.getUsrMemberController().actionJoin(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/login")) {
        Container.getUsrMemberController().actionLogin(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/logout")) {
        Container.getUsrMemberController().actionLogout(rq);
      }
      else if(rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    sc.close();
  }

  private boolean runInterceptor(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(Container.getNeedLoginInterceptor());
    interceptors.add(Container.getNeedLogoutInterceptor());

    for(Interceptor interceptor : interceptors) {
      if(interceptor.run(rq) == false) {
        return false;
      }
    }

    return true;
  }
}
