package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Easy;

/**
 * Reverse digits of an integer.
 *
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 *
 * Have you thought about this?
 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 *
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 *
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
 *
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger implements Easy {
  public static class Solution {
    public int reverse(int x) {
      boolean neg = x < 0;
      if(neg)
        x = -x;
      long result = 0;
      while(x != 0) {
        int d = x % 10;
        x = x / 10;
        result *= 10;
        result += d;
      }
      if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
        result = 0;
      return (int) (neg ? -result : result);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().reverse(123));
  }
}
