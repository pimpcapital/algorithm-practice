package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative.
 */
public class _043_MultiplyStrings implements Medium {

  public interface Solution {
    String multiply(String num1, String num2);
  }

  public static class Solution1 implements Solution {
    @Override
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

  public static class Solution2 implements Solution {

    @Override
    public String multiply(String num1, String num2) {
      int[][] multiples = new int[num2.length()][num1.length() + 1];
      for(int r = 0; r < num2.length(); r++) {
        int n2 = num2.charAt(num2.length() - 1 - r) - '0';
        for(int c = num1.length() - 1; c >= 0; c--) {
          int n1 = num1.charAt(c) - '0';
          multiples[r][c + 1] = n2 * n1; // intentionally leave first digit of every row to 0
        }
      }
      String[] rows = toRows(multiples);
      return sumRows(rows);
    }

    private static String[] toRows(int[][] multiples) {
      String[] ret = new String[multiples.length];
      for(int i = 0; i < multiples.length; i++) ret[i] = toRow(multiples[i], i);
      return ret;
    }

    private static String toRow(int[] values, int trailingZeroes) {
      normalize(values);
      StringBuilder ret = new StringBuilder();
      for(int value : values) {
        if(ret.length() == 0 && value == 0) continue;
        ret.append(value);
      }
      if(ret.length() > 0) {
        while(trailingZeroes-- > 0) ret.append('0');
      }
      return ret.length() == 0 ? "0" : ret.toString();
    }

    private static String addRows(String a, String b) {
      int[] sums = new int[Math.max(a.length(), b.length()) + 1];
      for(int i = 0; i < a.length(); i++) {
        sums[sums.length - 1 - i] = a.charAt(a.length() - 1 - i) - '0';
      }
      for(int i = 0; i < b.length(); i++) {
        sums[sums.length - 1 - i] += b.charAt(b.length() - 1 -i) - '0';
      }
      normalize(sums);
      StringBuilder ret = new StringBuilder();
      for(int sum : sums) {
        if(sum == 0 && ret.length() == 0) continue;
        ret.append(sum);
      }
      return ret.length() == 0 ? "0" : ret.toString();
    }

    private static String sumRows(String[] rows) {
      String current = "0";
      for(String row : rows) current = addRows(current, row);
      return current;
    }

    private static void normalize(int[] values) {
      for(int i = values.length - 1; i > 0; i--) {
        values[i - 1] += values[i] / 10;
        values[i] %= 10;
      }
    }

  }

  public static class Solution3 implements Solution {
    @Override
    public String multiply(String num1, String num2) {
      int m = num1.length(), n = num2.length();
      int[] pos = new int[m + n];

      for(int i = m - 1; i >= 0; i--) {
        for(int j = n - 1; j >= 0; j--) {
          int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
          int p1 = i + j, p2 = p1 + 1;
          int sum = mul + pos[p2];

          pos[p1] += sum / 10;
          pos[p2] = sum % 10;
        }
      }

      StringBuilder sb = new StringBuilder();
      for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
      return sb.length() == 0 ? "0" : sb.toString();
    }
  }

  public static void main(String args[]) {
    String num1;
    String num2;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num1 = "123";
      num2 = "456";
      result = s.multiply(num1, num2);
      System.out.println(result);

      num1 = "9";
      num2 = "9";
      result = s.multiply(num1, num2);
      System.out.println(result);

      num1 = "99999";
      num2 = "0";
      result = s.multiply(num1, num2);
      System.out.println(result);
    }
  }
}
