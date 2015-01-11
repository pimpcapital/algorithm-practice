package com.beijunyi.leetcode;

public class ReverseInteger {
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
