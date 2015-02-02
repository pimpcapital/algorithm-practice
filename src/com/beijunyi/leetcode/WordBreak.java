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

  public static class Solution {


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

  public static void main(String args[]) {
//    System.out.println(new Solution().wordBreak("leetcode", new HashSet<>(Arrays.asList("leet", "code"))));
    System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"))));
  }

}
