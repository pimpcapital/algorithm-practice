package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *   1 -> A
 *   2 -> B
 *   3 -> C
 *   ...
 *   26 -> Z
 *   27 -> AA
 *   28 -> AB
 */
public class _168_ExcelSheetColumnTitle implements Easy, NotHardButTricky {

  public interface Solution {
    String convertToTitle(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public String convertToTitle(int n) {
      StringBuilder ret = new StringBuilder();
      while(n-- > 0) {
        char letter = (char) (n % 26 + 'A');
        n /= 26;
        ret.append(letter);
      }
      return ret.reverse().toString();
    }
  }

  public static void main(String args[]) {
    int n;
    String result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 1;
      result = s.convertToTitle(n);
      System.out.println(result);

      n = 26;
      result = s.convertToTitle(n);
      System.out.println(result);

      n = 27;
      result = s.convertToTitle(n);
      System.out.println(result);

      n = 28;
      result = s.convertToTitle(n);
      System.out.println(result);

      n = 52;
      result = s.convertToTitle(n);
      System.out.println(result);
    }
  }

}
