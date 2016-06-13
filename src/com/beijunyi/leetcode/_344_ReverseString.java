package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Write a function that takes a string as input and returns the string reversed.
 *
 * Example:
 * Given s = "hello", return "olleh".
 */
public class _344_ReverseString implements Easy {

  public interface Solution {
    String reverseString(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public String reverseString(String s) {
      char[] reversed = new char[s.length()];
      for(int i = 0; i < s.length(); i++) reversed[s.length() - 1 - i] = s.charAt(i);
      return new String(reversed);
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public String reverseString(String s) {
      return  new StringBuilder(s).reverse().toString();
    }

  }

  public static void main(String args[]) {
    String str;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "hello";
      result = s.reverseString(str);
      System.out.println(result);
    }
  }


}
