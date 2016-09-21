package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;
import com.beijunyi.leetcode.category.solution.Memoization;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 *
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the
 * number I picked.
 *
 * Example:
 *   n = 10, I pick 8.
 *
 *   First round:  You guess 5, I tell you that it's higher. You pay $5.
 *   Second round: You guess 7, I tell you that it's higher. You pay $7.
 *   Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *
 *   Game over. 8 is the number I picked.
 *
 *   You end up paying $5 + $7 + $9 = $21.
 *
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 * Hint:
 *   1) The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is
 *      to minimize the expected loss. Here, we are interested in the first scenario.
 *   2) Take a small example (n = 3). What do you end up paying in the worst case?
 *   3) Check out this article if you're still stuck.
 *   4) The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic
 *      programming.
 *   5) As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of
 *      the worst-case loss?
 */
public class _375_GuessNumberHigherOrLowerTwo implements Medium {

  public interface Solution {
    int getMoneyAmount(int n);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public int getMoneyAmount(int n) {
      int[][] cache = new int[n + 1][n + 1];
      for(int end = 2; end <= n; end++) {
        for(int start = end - 1; start >= 1; start--) {
          computeMinGuessCost(start, end, cache);
        }
      }
      return cache[1][n];
    }

    private void computeMinGuessCost(int start, int end, int[][] cache) {
      int guess = start + (end - start) / 2;
      int min = Integer.MAX_VALUE;
      while(guess < end) {
        int leftEnd = guess - 1;
        int rightStart = guess + 1;

        int cost = guess + Math.max(cache[start][leftEnd], cache[rightStart][end]);
        if(cost < min) min = cost;
        guess++;
      }
      cache[start][end] = min;
    }

  }

  public static class Solution2 implements Solution, Memoization {

    @Override
    public int getMoneyAmount(int n) {
      int[][] dp = new int[n + 1][n + 1];
      return computeMinGuessCost(1, n, dp);
    }

    private static int computeMinGuessCost(int start, int end, int[][] dp) {
      if(dp[start][end] != 0) return dp[start][end];
      if(start == end) return 0;
      if(start >= end - 1)  return dp[start][end] = end - 1;

      int guess = (start + end) / 2;
      int min = Integer.MAX_VALUE;
      while(guess < end) {
        int left = computeMinGuessCost(start, guess - 1, dp);
        int right = computeMinGuessCost(guess + 1, end, dp);
        min = Math.min(min, guess + Math.max(left, right));
        guess++;
      }
      return dp[start][end] = min;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 1;
      result = s.getMoneyAmount(n);
      System.out.println(result); // 0

      n = 2;
      result = s.getMoneyAmount(n);
      System.out.println(result); // 1

      n = 3;
      result = s.getMoneyAmount(n);
      System.out.println(result); // 2

      n = 4;
      result = s.getMoneyAmount(n);
      System.out.println(result);

      n = 5;
      result = s.getMoneyAmount(n);
      System.out.println(result);

      n = 6;
      result = s.getMoneyAmount(n);
      System.out.println(result);

      n = 7;
      result = s.getMoneyAmount(n);
      System.out.println(result);

      n = 12;
      result = s.getMoneyAmount(n);
      System.out.println(result); //21

      n = 13;
      result = s.getMoneyAmount(n);
      System.out.println(result); //24

      n = 15;
      result = s.getMoneyAmount(n);
      System.out.println(result); //30

      System.out.println();
    }
  }

}
