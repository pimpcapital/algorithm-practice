package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those
 * integers. Return the maximum product you can get.
 *
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *
 * ote: you may assume that n is not less than 2.
 */
public class _343_IntegerBreak implements Medium {

  public interface Solution {
    int integerBreak(int n);
  }

  public static class Solution1 implements Solution {

    @Override
    public int integerBreak(int n) {
      if(n <= 6) {
        int first = n / 2;
        int second = n - first;
        return first * second;
      }
      int countOfThree = n / 3;
      int remain = n % 3;
      if(remain == 1) {
        countOfThree--;
        remain += 3;
      } else if(remain == 0) {
        remain = 1;
      }
      return (int) Math.pow(3, countOfThree) * remain;
    }

  }

  /**
   * If an optimal product contains a factor f >= 4, then you can replace it with factors 2 and f-2 without losing
   * optimality, as 2*(f-2) = 2f-4 >= f. So you never need a factor greater than or equal to 4, meaning you only need
   * factors 1, 2 and 3 (and 1 is of course wasteful and you'd only use it for n=2 and n=3, where it's needed).
   */
  public static class Solution2 implements Solution {

    @Override
    public int integerBreak(int n) {
      if (n == 2) return 1;
      if (n == 3) return 2;
      if (n % 3 == 0) return (int)Math.pow(3, n / 3);
      if (n % 3 == 1) return (int)Math.pow(3, (n - 4) / 3) * 4;// we cannot have a small number that is equal to 1. Then use 4 instead.
      return (int)Math.pow(3, (n - 2) / 3) * 2;
    }
  }

  public static class Solution3 implements Solution, DynamicPrograming {

    @Override
    public int integerBreak(int n) {
      int[] dp = new int[n + 1];
      dp[1] = 1;
      for(int i = 2; i <= n; i++) {
        for(int j = 1; 2 * j <= i; j++) {
          dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
        }
      }
      return dp[n];
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      n = 2;
      result = s.integerBreak(n);
      System.out.println(result);

      n = 10;
      result = s.integerBreak(n);
      System.out.println(result);
    }
  }

}
