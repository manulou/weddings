package com.manulsoftware.weddings;

/**
 * Created on 03/11/2016.
 * Jira: AXNUIREF-
 *
 * @author Vlad
 * @version V16.11
 */
public class NegaBinary {

  public static void main(String[] args) {
    int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i : array) {
      // System.out.println(i + " - " + negabinary(array[i]));
    }

    System.out.println(negabinary(9));
  }


  static String negabinary(int value) {
    String result = "";

    while (value != 0) {
      int remainder = value % -2;
      value = value / -2;

      if (remainder < 0) {
        remainder += 2;
        value += 1;
      }

      result = remainder + result;
    }

    return result;
  }

}
