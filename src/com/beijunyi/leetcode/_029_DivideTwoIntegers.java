package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */
public class _029_DivideTwoIntegers implements Medium {

  /**
   * Long division in binary:
   *
   * The outer loop reduces n by at least half each iteration. So It has O(log N) iterations.
   * The inner loop has at most log N iterations. So the overall complexity is O((log N)^2)
   */
  public static class Solution {
    public int divide(int dividend, int divisor) {
      if(divisor == 0)
        return Integer.MAX_VALUE;

      if(divisor == 1)
        return dividend;

      if(divisor == -1)
        return (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -dividend;

      final boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

      long ldividend = Math.abs((long)dividend);
      long ldivisor = Math.abs((long)divisor);
      int result = 0;

      for(int bit = Integer.SIZE - 1; bit >= 0 && ldividend >= ldivisor; --bit) {
        if(ldividend >= (ldivisor << bit)) {
          ldividend -= ldivisor << bit;
          result |= 1 << bit;
        }
      }

      return negative ? -result : result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().divide(50, 5));
  }

}
