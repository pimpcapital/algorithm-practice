package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative.
 */
public class _043_MultiplyStrings implements Medium {

  public static class Solution {
    public String multiply(String num1, String num2) {
      int[] result = new int[num1.length() + num2.length()];
      for(int i = num1.length() - 1; i >= 0; i--) {
        int d1 = num1.charAt(i) - '0';
        for(int j = num2.length() - 1; j >= 0; j--) {
          int d2 = num2.charAt(j) - '0';
          int prod = d1 * d2;
          int pos = (num1.length() - 1 - i) + (num2.length() - 1 - j);
          result[pos] += prod % 10;
          result[pos + 1] += prod / 10;
        }
      }
      int carry = 0;
      int maxDigit = 0;
      for(int i = 0 ; i < result.length; i++) {
        result[i] += carry;
        carry = result[i] / 10;
        result[i] = result[i] % 10;
        if(result[i] != 0)
          maxDigit = i;
      }
      char[] strResult = new char[maxDigit + 1];
      int j = 0;
      for(int i = strResult.length - 1; i >= 0; i--) {
        strResult[j++] = (char) ('0' + result[i]);
      }
      return new String(strResult);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().multiply("9", "9"));
  }
}
