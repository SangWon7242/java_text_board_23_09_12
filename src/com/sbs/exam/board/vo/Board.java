package com.sbs.exam.board.vo;

public class Board {
  private int id;
  private String regDate;
  private String updateDate;
  private String name;
  private String code;

  public Board(int id, String regDate, String updateDate, String name, String code) {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.name = name;
    this.code = code;
  }

  public int getId() {
    return id;
  }

  public String getRegDate() {
    return regDate;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return "board{" +
        "id=" + id +
        ", regDate='" + regDate + '\'' +
        ", updateDate='" + updateDate + '\'' +
        ", name='" + name + '\'' +
        ", code='" + code + '\'' +
        '}';
  }
}
