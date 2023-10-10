package com.sbs.exam.board.controller;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.service.MemberService;
import com.sbs.exam.board.vo.Member;
import com.sbs.exam.board.vo.Rq;

public class UsrMemberController {
  private MemberService memberService;

  public UsrMemberController() {
    memberService = Container.getMemberService();
  }

  public void actionJoin(Rq rq) {
    System.out.println("== 회원 가입 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    System.out.printf("로그인 패스워드 : ");
    String loginPw = Container.getSc().nextLine();

    System.out.printf("로그인 패스워드 확인 : ");
    String loginPwConfirm = Container.getSc().nextLine();

    if(loginPw.equals(loginPwConfirm) == false) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    System.out.printf("이름 : ");
    String name = Container.getSc().nextLine();

    memberService.join(loginId, loginPw, name);

    System.out.printf("\"%s\"님 회원 가입을 환영합니다.\n", name);
  }

  public void actionLogin(Rq rq) {
    System.out.println("== 로그인 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberLoginId(loginId);

    if(member == null) {
      System.out.println("해당 아이디는 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 패스워드 : ");
    String loginPw = Container.getSc().nextLine();

    if(loginPw.trim().length() == 0) {
      System.out.println("로그인 패스워드를 입력해주세요.");
      return;
    }

    if(member.getLoginPw().equals(loginPw) == false) {
      System.out.println("로그인 패스워드가 일치하지 않습니다.");
      System.out.println("다시 확인 후 입력해주세요.");
      return;
    }

    rq.login(member);

    System.out.printf("\"%s\"님 환영합니다.\n", member.getName());
  }

  public void actionLogout(Rq rq) {
    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
