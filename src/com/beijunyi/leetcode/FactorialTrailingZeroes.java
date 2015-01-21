package com.beijunyi.leetcode;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note: Your solution should be in logarithmic time complexity.
 */
public class FactorialTrailingZeroes {

  public static class Solution {
    public int trailingZeroes(int n) {
      int result = 0;
      do {
        result += n / 5;
        n /= 5;
      } while(n >= 5);
      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().trailingZeroes(2147483647));
  }


}
