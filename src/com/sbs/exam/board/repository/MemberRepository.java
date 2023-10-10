package com.sbs.exam.board.repository;

import com.sbs.exam.board.vo.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private int lastId;
  private List<Member> members;

  public MemberRepository() {
    lastId = 0;
    members = new ArrayList<>();
  }

  public void join(String loginId, String loginPw, String name) {
    int id = lastId + 1;
    Member member = new Member(id, loginId, loginPw, name);
    members.add(member);
    lastId = id;
  }

  public Member getMemberLoginId(String loginId) {
    for(Member member : members) {
      if(member.getLoginId().equals(loginId)) {
        return member;
      }
    }

    return null;
  }

  public Member getMemberById(int id) {
    for(Member member : members) {
      if(member.getId() == id) {
        return member;
      }
    }

    return null;
  }
}
