package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */
public class _044_WildcardMatching implements Hard {

  public interface Solution {
    boolean isMatch(String str, String pattern);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isMatch(String str, String pattern) {
      int s = 0, p = 0, match = 0, starIdx = -1;
      while (s < str.length()){
        // advancing both pointers
        if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
          s++;
          p++;
        }
        // * found, only advancing pattern pointer
        else if (p < pattern.length() && pattern.charAt(p) == '*'){
          starIdx = p;
          match = s;
          p++;
        }
        // last pattern pointer was *, advancing string pointer
        else if (starIdx != -1){
          p = starIdx + 1;
          match++;
          s = match;
        }
        //current pattern pointer is not star, last patter pointer was not *
        //characters do not match
        else return false;
      }

      //check for remaining characters in pattern
      while (p < pattern.length() && pattern.charAt(p) == '*')
        p++;

      return p == pattern.length();
    }
  }

  public static class Solution2 implements Solution, Backtracking, DynamicPrograming {

    @Override
    public boolean isMatch(String str, String pattern) {
      boolean[][] avoid = new boolean[str.length() + 1][pattern.length() + 1];
      return isMatch(str, 0, pattern, 0, avoid);
    }

    private static boolean isMatch(String str, int sOffset, String pattern, int pOffset, boolean[][] avoid) {
      if(pattern.length() == pOffset) return str.length() == sOffset;
      if(avoid[sOffset][pOffset]) return false;
      boolean result = false;
      char p = pattern.charAt(pOffset);
      if(p == '*') {
        for(int nextS = sOffset; nextS <= str.length(); nextS++) {
          if(isMatch(str, nextS, pattern, pOffset + 1, avoid)) {
            result = true;
            break;
          }
        }
      } else if(sOffset < str.length()) {
        char s = str.charAt(sOffset);
        if(p == '?' || p == s) {
          result = isMatch(str, sOffset + 1, pattern, pOffset + 1, avoid);
        }
      }
      if(!result) avoid[sOffset][pOffset] = true;
      return result;
    }

  }

  public static void main(String args[]) {
    String str;
    String pattern;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "";
      pattern = "*";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aa";
      pattern = "*";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "ab";
      pattern = "*?";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "cbcdcd";
      pattern = "*b*cd";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      System.out.println();

    }
  }

}
