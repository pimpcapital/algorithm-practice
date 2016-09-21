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
      if(n <= 1) return 0;
      int[][] cache = new int[n + 1][2]; // [amount, depth]
      cache[0] = new int[2];
      cache[1] = new int[2];
      for(int i = 2; i <= n; i++) calculateTotal(i, cache);
      return cache[n][0];
    }

    private void calculateTotal(int n, int[][] cache) {
      int guess = n / 2;
      int[] min = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
      while(guess < n) {
        int left = guess - 1;
        int leftDepth = cache[left][1];
        int leftTotal = cache[left][0];

        int right = n - 1 - left;
        int rightDepth = cache[right][1];
        int rightTotal = cache[right][0] + rightDepth * guess;

        int total;
        int depth;
        if(leftTotal > rightTotal) {
          total = leftTotal + guess;
          depth = leftDepth + 1;
        } else {
          total = rightTotal + guess;
          depth = rightDepth + 1;
        }
        if(total < min[0]) {
          min[0] = total;
          min[1] = depth;
          guess++;
        } else break;
      }
      cache[n] = min;
    }

  }

  public static class Solution2 implements Solution, Memoization {

    private int[][] dp;

    @Override
    public int getMoneyAmount(int n) {
      dp = new int[n + 1][n + 1];
      return helper(1, n);
    }

    private int helper(int start, int end) {
      if(dp[start][end] != 0) {
        return dp[start][end];
      }
      if(start >= end) {
        return 0;
      }
      if(start >= end - 2) {
        return dp[start][end] = end - 1;
      }
      int mid = (start + end) / 2 - 1, min = Integer.MAX_VALUE;
      while(mid < end) {
        int left = helper(start, mid - 1);
        int right = helper(mid + 1, end);
        min = Math.min(min, mid + Math.max(left, right));
        if (right <= left) break;
        mid++;
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
