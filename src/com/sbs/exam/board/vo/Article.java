package com.sbs.exam.board.vo;

public class Article {
  private int id;
  private int boardId;
  private String title;
  private String content;

  public Article(int id, int boardId, String title, String content) {
    this.id = id;
    this.boardId = boardId;
    this.title = title;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public int getBoardId() {
    return boardId;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", boardId=" + boardId +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
