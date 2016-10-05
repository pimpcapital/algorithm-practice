package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class _161_OneEditDistance implements Medium, PremiumQuestion {

  public interface Solution {
    boolean isOneEditDistance(String s, String t);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isOneEditDistance(String s, String t) {
      if(t.length() > s.length()) return isOneEditDistance(t, s);
      if(s.length() - t.length() > 1) return false;

      boolean edited = false;
      int i = 0, j = 0;
      for(; i < s.length() && j < t.length(); i++, j++) {
        if(s.charAt(i) == t.charAt(j)) continue;
        if(edited) return false;
        if(s.length() > t.length()) j--; // suppress the j increment
        edited = true;
      }

      return edited && i == s.length() || !edited && i == s.length() - 1;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public boolean isOneEditDistance(String s, String t) {
      for(int i = 0; i < Math.min(s.length(), t.length()); i++) {
        if (s.charAt(i) != t.charAt(i)) {
          if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
            return s.substring(i + 1).equals(t.substring(i + 1));
          else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
            return s.substring(i).equals(t.substring(i + 1));
          else // s is longer than t, so the only possibility is deleting one char from s
            return t.substring(i).equals(s.substring(i + 1));
        }
      }
      //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
      return Math.abs(s.length() - t.length()) == 1;
    }
  }

  public static void main(String args[]) {
    String s;
    String t;
    boolean result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "a";
      t = "";
      result = solution.isOneEditDistance(s, t);
      System.out.println(result);

      s = "a";
      t = "ac";
      result = solution.isOneEditDistance(s, t);
      System.out.println(result);

      s = "ab";
      t = "acd";
      result = solution.isOneEditDistance(s, t);
      System.out.println(result);
    }

  }
}
