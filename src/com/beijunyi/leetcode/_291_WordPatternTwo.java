package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring
 * in str.
 *
 * Examples:
 *   pattern = "abab", str = "redblueredblue" should return true.
 *   pattern = "aaaa", str = "asdasdasdasd" should return true.
 *   pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 *   You may assume both pattern and str contains only lowercase letters.
 */
public class _291_WordPatternTwo implements Hard, PremiumQuestion {

  public interface Solution {
    boolean wordPatternMatch(String pattern, String str);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    @Override
    public boolean wordPatternMatch(String pattern, String str) {
      int[][] map = new int[26][];
      return wordPatternMatch(pattern.toCharArray(), 0, str.toCharArray(), 0, map);
    }

    private static boolean wordPatternMatch(char[] pattern, int pOffset, char[] str, int sOffset, int[][] map) {
      if(pOffset == pattern.length) return sOffset == str.length;
      int next = pattern[pOffset] - 'a';
      int[] mapped = map[next]; // subarray to compare [start, length]
      if(mapped != null) { // is not new letter
        return mapped[1] + sOffset <= str.length
                 && charsEqual(str, sOffset, mapped)
                 && wordPatternMatch(pattern, pOffset + 1, str, sOffset + mapped[1], map);
      } else {
        mapped = new int[]{sOffset, 0};
        map[next] = mapped;
        int max = str.length - sOffset;
        for(mapped[1] = 1; mapped[1] <= max; mapped[1]++) {
          if(wordPatternMatch(pattern, pOffset, str, sOffset, map) && isMapValid(str, map)) return true;
        }
        map[next] = null;
        return false;
      }
    }

    private static boolean charsEqual(char[] str, int offset, int[] mapped) {
      int start = mapped[0];
      int length = mapped[1];
      for(int i = 0; i < length; i++) {
        if(str[offset + i] != str[start + i]) return false;
      }
      return true;
    }

    private static boolean isMapValid(char[] str, int[][] map) { // no character in pattern mapped to same string
      Set<String> mappedStrs = new HashSet<>();
      for(int[] startLength : map) {
        if(startLength == null) continue;
        if(!mappedStrs.add(new String(str, startLength[0], startLength[1])))
          return false;
      }
      return true;
    }

  }

  public static void main(String args[]) {
    String pattern;
    String str;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      pattern = "abab";
      str = "redblueredblue";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "aaaa";
      str = "asdasdasdasd";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "aabb";
      str = "xyzabcxzyabc";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "abba";
      str = "dogcatcatdog";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "d";
      str = "e";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "ab";
      str = "aa";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "abab";
      str = "redblueredblue";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);

      pattern = "edcs";
      str = "electronicengineeringcomputerscience";
      result = s.wordPatternMatch(pattern, str);
      System.out.println(result);
    }
  }

}
