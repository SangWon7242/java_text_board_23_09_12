package com.sbs.exam.board.service;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.repository.BoardRepository;
import com.sbs.exam.board.vo.Board;

public class BoardService {
  private BoardRepository boardRepository;
  public BoardService() {
    boardRepository = Container.getBoardRepository();

    makeTestDate();
  }

  public void makeTestDate() {
    make("notice", "공지사항");
    make("free", "자유");
  }

  private int make(String code, String name) {
    return boardRepository.make(code, name);
  }

  public Board getBoardById(int boardId) {
    return boardRepository.getBoardById(boardId);
  }
}
