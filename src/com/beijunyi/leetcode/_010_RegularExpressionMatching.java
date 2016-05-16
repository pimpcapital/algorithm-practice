package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 *  isMatch("aa","a") → false
 *  isMatch("aa","aa") → true
 *  isMatch("aaa","aa") → false
 *  isMatch("aa", "a*") → true
 *  isMatch("aa", ".*") → true
 *  isMatch("ab", ".*") → true
 *  isMatch("aab", "c*a*b") → true
 */
public class _010_RegularExpressionMatching implements Hard {

  public interface Solution {
    boolean isMatch(String s, String p);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isMatch(String s, String p) {
      List<Token> tokens = compile(p);
      return matchTail(s, 0, tokens, 0);
    }

    private static List<Token> compile(String pattern) {
      List<Token> ret = new ArrayList<>();
      StringBuilder value = null;
      for(int i = 0; i < pattern.length(); i++) {
        char c = pattern.charAt(i);
        switch(c) {
          case '*':
            if(value == null)
              throw new IllegalStateException();
            ret.add(Token.match(value.toString(), true));
            value = null;
            break;
          default:
            if(value != null)
              ret.add(Token.match(value.toString(), false));
            value = new StringBuilder(Character.toString(c));
            break;
        }
      }
      if(value != null)
        ret.add(Token.match(value.toString(), false));
      return ret;
    }

    private static boolean matchTail(String str, int strOffset, List<Token> tokens, int tokensOffset) {
      if(tokensOffset == tokens.size()) // no more token
        return str.length() == strOffset;

      Token next = tokens.get(tokensOffset);
      for(int count = next.min; count <= next.max; count++) {
        if(matchHead(str, strOffset, next.value, count)) {
          int tailStart = strOffset + count * next.value.length();
          if(tailStart > str.length())
            break;
          if(matchTail(str, tailStart, tokens, tokensOffset + 1))
            return true;
        } else {
          break;
        }
      }
      return false;
    }

    // if "str" (starting from "offset") matches "value" X times
    private static boolean matchHead(String str, int offset, String value, int x) {
      for(int i = 0; i < x; i++) {
        if(!".".equals(value) && !str.startsWith(value, offset))
          return false;
        offset += value.length();
      }
      return true;
    }

    private static class Token {
      public final String value;
      public final int min;
      public final int max;

      private Token(String value, int min, int max) {
        this.value = value;
        this.min = min;
        this.max = max;
      }

      private static Token match(String str, boolean anyCount) {
        return new Token(str, anyCount ? 0 : 1, anyCount ? Integer.MAX_VALUE : 1);
      }

    }

  }

  public static class Solution2 implements Solution {
    @Override
    public boolean isMatch(String s, String p) {
      for(int i = 0; i < p.length(); s = s.substring(1)) {
        char c = p.charAt(i);
        if(i + 1 == p.length() || p.charAt(i + 1) != '*') // if next is not '*'
          i++;
        else if(isMatch(s, p.substring(i + 2)))
          return true;

        if(s.isEmpty()) // string finishes before pattern does
          return false;
        if(c != '.' && c != s.charAt(0)) // not match
          return false;
      }

      return s.isEmpty(); // string finishes when pattern finishes
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public boolean isMatch(String s, String p) {
    /*
        'match' below including .
    f(i,j) means s where s.len=i matches p where p.len=j
    f(i,j) =
        if (p_j-1 != * ) f(i-1, j-1) and s_i-1 matches p_j-1
        if (p_j-1 == * )
            * matches zero times: f(i,j-2)
            or * matches at least one time: f(i-1,j) and s_i-1 matches p_j-2
     */
      if(!p.isEmpty() && p.charAt(0) == '*') {
        return false;   // invalid p
      }

      boolean[][] f = new boolean[s.length() + 1][p.length() + 1];

      // initialize f(0,0)
      f[0][0] = true;

      // f(k,0) and f(0,2k-1) where k>=1 are false by default

      // initialize f(0,2k) where p_2k-1 = * for any k>=1
      for(int j = 1; j < p.length(); j += 2) {
        if(p.charAt(j) == '*') {
          f[0][j + 1] = f[0][j - 1];
        }
      }

      for(int i = 1; i <= s.length(); i++) {
        for(int j = 1; j <= p.length(); j++) {
          if(p.charAt(j - 1) != '*') {
            f[i][j] = f[i - 1][j - 1] && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
          } else {
            f[i][j] = f[i][j - 2] || f[i - 1][j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
          }
        }
      }

      return f[s.length()][p.length()];
    }

    // no * in p
    private boolean isCharMatch(char s, char p) {
      return p == '.' || s == p;
    }
  }

  public static void main(String args[]) {
    String str;
    String pattern;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      str = "aa";
      pattern = "a";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aa";
      pattern = "aa";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aaa";
      pattern = "aa";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aa";
      pattern = "a*";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aa";
      pattern = ".*";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "ab";
      pattern = ".*";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "aab";
      pattern = "c*a*b";
      result = s.isMatch(str, pattern);
      System.out.println(result);

      str = "ab";
      pattern = ".*c";
      result = s.isMatch(str, pattern);
      System.out.println(result);
    }
  }

}
