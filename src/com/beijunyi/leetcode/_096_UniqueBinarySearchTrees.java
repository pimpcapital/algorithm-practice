package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

public class _096_UniqueBinarySearchTrees implements Medium {
  public static class Solution1 {
    public int numTrees(int n) {
      int[] dp = new int[n+1];
      dp[0] = dp[1] = 1;
      for (int i=2; i<=n; i++) {
        dp[i] = 0;
        for (int j=1; j<=i; j++) {
          dp[i] += dp[j-1] * dp[i-j];
        }
      }
      return dp[n];
    }
  }

  public static class Solution2 {
    public int numTrees(int n) {
      int[] cache = new int[n + 1];
      Arrays.fill(cache, -1);
      cache[0] = 1;
      cache[1] = 1;
      return numTrees(n, cache);
    }
    public int numTrees(int n, int[] cache) {
      if(cache[n] != -1)
        return cache[n];
      int count = 0;
      for(int val = 1; val <= n; val++) {
        count += numTrees(val - 1, cache) * numTrees(n - val, cache);
      }
      cache[n] = count;
      return count;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().numTrees(3));
    System.out.println(new Solution2().numTrees(3));
  }
}
