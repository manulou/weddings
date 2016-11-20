package com.manulsoftware.weddings;

/**
 * Created on 03/11/2016.
 * Jira: AXNUIREF-
 *
 * @author Vlad
 * @version V16.11
 */
public class Task1 {

  public static void main(String[] args) {
    int[] array = new int[] {5, 5, 2, 3, 3, 5, 1, 6};
    System.out.println(solve(array, 5));
  }

  private static int solve(int[] array, int x) {
    int[] notNumberCountArray = new int[array.length];

    int notNumberCount = 0;
    for (int i = array.length - 1; i >= 0; i--) {
      if (array[i] != x) {
        ++notNumberCount;
      }
      notNumberCountArray[i] = notNumberCount;
    }

    int numberCount = 0;
    for (int i = 0; i < array.length; i++) {
      if (numberCount == notNumberCountArray[i + 1]) {
        return i;
      }
      if (array[i] == x) {
        numberCount++;
      }
    }

    return -1;
  }

}
