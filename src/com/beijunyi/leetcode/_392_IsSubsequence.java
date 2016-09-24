package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~=
 * 500,000) string, and s is a short string (<=100).
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of
 * "abcde" while "aec" is not).
 *
 * Example 1:
 *   s = "abc", t = "ahbgdc"
 *   Return true.
 *
 * Example 2:
 *   s = "axc", t = "ahbgdc"
 *   Return false.
 *
 * Follow up:
 *   If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T
 *   has its subsequence. In this scenario, how would you change your code?
 */
public class _392_IsSubsequence implements Medium {

  public interface Solution {
    boolean isSubsequence(String s, String t);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean isSubsequence(String s, String t) {
      int si = 0;
      int ti = 0;
      while(si < s.length() && ti < t.length()) {
        if(s.charAt(si) == t.charAt(ti)) si++;
        ti++;
      }
      return si == s.length();
    }
  }

  public static void main(String args[]) {
    String s;
    String t;
    boolean result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "abc";
      t = "ahgbdc";
      result = solution.isSubsequence(s, t);
      System.out.println(result);
    }
  }

}
