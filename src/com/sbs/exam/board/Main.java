package com.sbs.exam.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("== 프로그램 시작 ==");
    System.out.printf("명령) ");
    String cmd = sc.nextLine();
    System.out.printf("입력 된 명령어 : %s\n", cmd);
    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }
}