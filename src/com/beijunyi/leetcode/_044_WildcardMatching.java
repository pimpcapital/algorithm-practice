package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

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

  public static class Solution {
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

  public static void main(String args[]) {
    System.out.println(new Solution().isMatch("ab", "*?"));
  }

}
