package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *   A -> 1
 *   B -> 2
 *   C -> 3
 *   ...
 *   Z -> 26
 *   AA -> 27
 *   AB -> 28
 */
public class _171_ExcelSheetColumnNumber implements Easy {

  public interface Solution {
    int titleToNumber(String s);
  }

  public static class Solution1 implements Solution {
    @Override
    public int titleToNumber(String s) {
      int value = 0;
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        value *= 26;
        value += (c - 'A' + 1);
      }
      return value;
    }
  }

  public static void main(String args[]) {
    String str;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      str = "AZ";
      result = s.titleToNumber(str);
      System.out.println(result);
    }
  }

}
