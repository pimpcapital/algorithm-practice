package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;
import com.beijunyi.leetcode.category.solution.GreedyAlgorithm;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
 * sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class _279_PerfectSquares implements Medium, Important {

  public interface Solution {
    int numSquares(int n);
  }

  public static class Solution1 implements Solution, GreedyAlgorithm, Backtracking {

    @Override
    public int numSquares(int n) {
      return numSquaresWithLimit(n, n);
    }

    private static int numSquaresWithLimit(int n, int limit) {
      if(limit == 0) return 0; // no chance to get a better answer
      if(n == 0) return 0; // problem solved

      int min = limit;
      for(int r = (int) Math.sqrt(n); r >= 1; r--) {
        min = Math.min(min, 1 + numSquaresWithLimit(n - r * r, min - 1));
      }
      return min;
    }

  }

  // DP top down
  public static class Solution2 implements Solution, DynamicPrograming {

    @Override
    public int numSquares(int n) {
      int[] cache = new int[n + 1];
      return numSquaresWithCache(n, cache);
    }

    private static int numSquaresWithCache(int n, int[] cache) {
      if(n == 0) return 0; // problem solved
      if(cache[n] != 0) return cache[n];

      int min = n;
      for(int r = 1; r * r <= n; r++) {
        min = Math.min(min, 1 + numSquaresWithCache(n - r * r, cache));
      }

      cache[n] = min;
      return min;
    }

  }

  // DP bottom up
  public static class Solution3 implements Solution, DynamicPrograming {

    @Override
    public int numSquares(int n) {
      int[] memo = new int[n + 1];
      Arrays.fill(memo, Integer.MAX_VALUE);
      memo[0] = 0;
      for(int i = 1; i * i <= n; i++) {
        int sq = i * i;
        for(int j = sq; j < memo.length; j++) {
          int remain = j - sq;
          memo[j] = Math.min(memo[j], memo[remain] + 1);
        }
      }
      return memo[n];

    }

  }

  // DP bottom up
  public static class Solution4 implements Solution, DynamicPrograming {

    @Override
    public int numSquares(int n) {
      if(n <= 1) return n;
      int[] dp = new int[n + 1];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      for(int i = 1; i <= n; i++) {
        for(int r = 1; r * r <= i; r++) {
          dp[i] = Math.min(dp[i], 1 + dp[i - r * r]);
        }
      }
      return dp[n];
    }

  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4())) {
      n = 12;
      result = s.numSquares(n);
      System.out.println(result);

      n = 13;
      result = s.numSquares(n);
      System.out.println(result);

      n = 28;
      result = s.numSquares(n);
      System.out.println(result);

      System.out.println();
    }
  }

}
