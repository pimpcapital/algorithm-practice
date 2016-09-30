package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;
import com.beijunyi.leetcode.category.solution.Memoization;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class _139_WordBreak implements Medium {

  public interface Solution {
    boolean wordBreak(String s, Set<String> dict);
  }

  public static class Solution1 implements Solution, DynamicPrograming {
    @Override
    public boolean wordBreak(String s, Set<String> dict) {
      boolean[] f = new boolean[s.length() + 1];
      Arrays.fill(f, false);

      f[0] = true;

      for(int end = 1; end <= s.length(); end++) {
        for(int start = 0; start < end; start++) {
          if(f[start] && dict.contains(s.substring(start, end))) {
            f[end] = true;
            break;
          }
        }
      }

      return f[s.length()];
    }
  }

  public static class Solution2 implements Solution, Memoization, Backtracking {
    @Override
    public boolean wordBreak(String s, Set<String> dict) {
      Boolean[] cache = new Boolean[s.length() + 1];
      cache[s.length()] = true;
      return wordBreak(s, 0, dict, cache);
    }

    private boolean wordBreak(String s, int start, Set<String> dict, Boolean[] cache) {
      if(cache[start] != null)
        return cache[start];
      for(int i = start + 1; i <= s.length(); i++) {
        String first = s.substring(start, i);
        if(dict.contains(first) && wordBreak(s, i, dict, cache)) {
          cache[start] = true;
          break;
        }
      }
      if(cache[start] == null)
        cache[start] = false;
      return cache[start];
    }
  }

  public static void main(String args[]) {
    String s;
    Set<String> dict;
    boolean result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
      dict = new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
      result = solution.wordBreak(s, dict);
      System.out.println(result);
    }
  }

}
