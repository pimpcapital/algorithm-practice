package com.beijunyi.leetcode;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.
 */
public class DistinctSubsequences {

  public static class Solution {
    public int numDistinct(String S, String T) {
      if (T.length() > S.length()) return 0;         // impossible for subsequence

      int[] path = new int[T.length() + 1];
      path[0] = 1;                                   // initial condition

      for(int s = 0; s < S.length(); s++)
        for(int t = T.length() - 1; t >= 0; t--)     // traversing backwards so we are using path[i] from last time step
          path[t + 1] = path[t + 1] + (T.charAt(t) == S.charAt(s) ? path[t] : 0);

      return path[T.length()];
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().numDistinct("rabbbit", "rabbit"));
  }
}
