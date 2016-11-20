package com.manulsoftware.weddings.web;

/**
 * Created on 03/11/2016.
 * Jira: AXNUIREF-
 *
 * @author Vlad
 * @version V16.11
 */
public class Test {

  public static void main(String[] args) {
    System.out.println(new Test().solution(64, 4));
  }

  public int solution(int x, int y) {
    if (x < 2 && y < 2) {
      return -1;
    }
    int diff = x - y;
    if (y > diff) {
      return 2 * ((int) Math.floor((y - diff) / 3)) + diff;
    } else {
      return diff - 2 * ((int) Math.floor((diff - y) / 4));
    }
  }

}
