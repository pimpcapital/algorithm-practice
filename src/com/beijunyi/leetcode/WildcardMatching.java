package com.beijunyi.leetcode;

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
public class WildcardMatching {

  public static class Solution {
    public boolean isMatch(String s, String p) {
      return isMatch(s, 0, p, 0);
    }

    public boolean isMatch(String s, int so, String p, int po) {
      for(; so < s.length() && po < p.length(); so++, po++) {
        char sc = s.charAt(so);
        char pc = p.charAt(po);
        if(sc == pc || pc == '?')
          continue;
        if(pc == '*') {
          for(int st = so; st < s.length(); st++)
            if(isMatch(s, st, p, po + 1))
              return true;
          return false;
        }
        return false;
      }
      return true;
    }
  }

}
