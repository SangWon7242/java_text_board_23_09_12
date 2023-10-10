package com.sbs.exam.board.repository;

import com.sbs.exam.board.vo.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {

  private int lastId;
  private List<Board> boards;

  public BoardRepository() {
    lastId = 0;
    boards = new ArrayList<>();
  }

  public Board getBoardById(int id) {
    for(Board board : boards) {
      if(board.getId() == id) {
        return board;
      }
    }

    return null;
  }

  public int make(String code, String name) {
    int id = lastId + 1;
    String regDate = "2023-10-10 09:41:33";
    String updateDate = regDate;
    Board board = new Board(id, regDate, updateDate, name, code);
    boards.add(board);
    lastId = id;

    return id;
  }
}
