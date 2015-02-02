package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak implements Medium {

  public static class Solution1 {
    public boolean wordBreak(String s, Set<String> dict) {
      boolean[] f = new boolean[s.length() + 1];
      Arrays.fill(f, false);

      f[0] = true;

      for(int i = 1; i <= s.length(); i++) {
        for(int j = 0; j < i; j++) {
          if(f[j] && dict.contains(s.substring(j, i))) {
            f[i] = true;
            break;
          }
        }
      }

      return f[s.length()];
    }
  }

  public static class Solution2 {
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
    System.out.println(new Solution1().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"))));
    System.out.println(new Solution2().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"))));
  }

}
