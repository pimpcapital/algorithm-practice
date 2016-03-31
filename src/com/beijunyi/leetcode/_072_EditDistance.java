package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

public class _072_EditDistance implements Hard {

  /**
   * Use f[i][j] to represent the shortest edit distance between word1[0,i) and word2[0, j). Then compare the last character of word1[0,i) and word2[0,j), which are c and d respectively (c == word1[i-1], d == word2[j-1]):
   * if c == d, then : f[i][j] = f[i-1][j-1]
   * Otherwise we can use three operations to convert word1 to word2:
   *
   * (a) if we replaced c with d: f[i][j] = f[i-1][j-1] + 1;
   * (b) if we added d after c: f[i][j] = f[i][j-1] + 1;
   * (c) if we deleted c: f[i][j] = f[i-1][j] + 1;
   * Note that f[i][j] only depends on f[i-1][j-1], f[i-1][j] and f[i][j-1], therefore we can reduce the space to O(n) by using only the (i-1)th array and previous updated element(f[i][j-1]).
   */
  public static class Solution {
    public int minDistance(String word1, String word2) {
      int len1 = word1.length();
      int len2 = word2.length();

      //distance[i][j] is the distance converse word1(1~ith) to word2(1~jth)
      int[][] distance = new int[len1 + 1][len2 + 1];
      for (int j = 0; j <= len2; j++)
        distance[0][j] = j; //delete all characters in word2
      for (int i = 0; i <= len1; i++)
        distance[i][0] = i;

      for (int i = 1; i <= len1; i++)
        for (int j = 1; j <= len2; j++)
          if (word1.charAt(i - 1) == word2.charAt(j - 1))  //ith & jth
            distance[i][j] = distance[i - 1][j - 1];
          else
            distance[i][j] = Math.min(Math.min(distance[i][j - 1], distance[i - 1][j]), distance[i - 1][j - 1]) + 1;
      return distance[len1][len2];
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().minDistance("abcde", "axxbxde"));
  }
}
