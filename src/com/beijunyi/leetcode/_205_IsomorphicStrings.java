package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No
 * two characters may map to the same character but a character may map to itself.
 *
 * For example,
 *   Given "egg", "add", return true.
 *   Given "foo", "bar", return false.
 *   Given "paper", "title", return true.
 *
 * Note:
 *   You may assume both s and t have the same length.
 */
public class _205_IsomorphicStrings implements Easy {

  public interface Solution {
    boolean isIsomorphic(String s, String t);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isIsomorphic(String s, String t) {
      char[] map = new char[128];
      boolean[] mapped = new boolean[128];
      for(int i = 0; i < s.length(); i++) {
        char sc = s.charAt(i);
        char tc = t.charAt(i);
        if(map[(int) sc] == 0) {
          map[(int) sc] = tc;
          if(mapped[(int) tc]) return false; // violation: many to 1
          mapped[(int) tc] = true;
        } else {
          if(map[(int) sc] != tc) return false; // violation: 1 to many
        }
      }
      return true;
    }

  }

  public static void main(String args[]) {
    String s;
    String t;
    boolean result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "ab";
      t = "aa";
      result = solution.isIsomorphic(s, t);
      System.out.println(result);
    }
  }

}
